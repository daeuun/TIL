jsp는 파일이 변경되는 경우, jsp를 다시 컴파일하기 때문에 별도의 서버변경없이 반영이 됩니다.   
하지만 xml파일은 서버가 시작되는 시점에 값을 참조하기 때문에 변경사항이 반영되지 않게 됩니다.

[나의 issue상황]

서버에서 xml파일 Update 받았을때 내가 가진 xml파일이랑 톰캣 서버에 올라간 xml이랑 매칭했을때 일치하지 않아서 계속 서버에서 확인하려고 했음  
⇒ 계속 오류 찍힘  
⇒ 서버 재실행하면 내가 가진 xml파일을 톰캣이 그대로 참조하기 때문에 오류가 안생기게됨

<br>

## **💡 .xml 파일 수정해서 서버 재실행 없이 XML을 적용 가능**

<br>

## 1. Java Class파일 생성하기

### **RefreshableSqlSessionFactoryBean.java**

```java
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;
 
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RefreshableSqlSessionFactoryBean extends SqlSessionFactoryBean implements DisposableBean {

    private static final Log log = LogFactory.getLog(RefreshableSqlSessionFactoryBean.class);

    private SqlSessionFactory proxy;
    private int interval = 500;

    private Timer timer;
    private TimerTask task;

    private Resource[] mapperLocations;

    /**
     * 파일 감시 쓰레드가 실행중인지 여부.
     */
    private boolean running = false;

    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    private final Lock r = rwl.readLock();
    private final Lock w = rwl.writeLock();

    public void setMapperLocations(Resource[] mapperLocations) {
        super.setMapperLocations(mapperLocations);
        this.mapperLocations = mapperLocations;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    /**
     * @throws Exception
     */
    public void refresh() throws Exception {
        if (log.isInfoEnabled()) {
            log.info("refreshing sqlMapClient.");
        }
        w.lock();
        try {
            super.afterPropertiesSet();

        } finally {
            w.unlock();
        }
    }

    /**
     * 싱글톤 멤버로 SqlMapClient 원본 대신 프록시로 설정하도록 오버라이드.
     */
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();

        setRefreshable();
    }

    private void setRefreshable() {
        proxy = (SqlSessionFactory) Proxy.newProxyInstance(
                SqlSessionFactory.class.getClassLoader(),
                new Class[]{SqlSessionFactory.class},
                new InvocationHandler() {
                    public Object invoke(Object proxy, Method method,
                                         Object[] args) throws Throwable {
                        // log.debug("method.getName() : " + method.getName());
                        return method.invoke(getParentObject(), args);
                    }
                });

        task = new TimerTask() {
            private Map<Resource, Long> map = new HashMap<Resource, Long>();

            public void run() {
                if (isModified()) {
                    try {
                        refresh();
                    } catch (Exception e) {
                        log.error("caught exception", e);
                    }
                }
            }

            private boolean isModified() {
                boolean retVal = false;

                if (mapperLocations != null) {
                    for (int i = 0; i < mapperLocations.length; i++) {
                        Resource mappingLocation = mapperLocations[i];
                        retVal |= findModifiedResource(mappingLocation);
                    }
                }

                return retVal;
            }

            private boolean findModifiedResource(Resource resource) {
                boolean retVal = false;
                List<String> modifiedResources = new ArrayList<String>();

                try {
                    long modified = resource.lastModified();

                    if (map.containsKey(resource)) {
                        long lastModified = ((Long) map.get(resource))
                                .longValue();

                        if (lastModified != modified) {
                            map.put(resource, new Long(modified));
                            modifiedResources.add(resource.getDescription());
                            retVal = true;
                        }
                    } else {
                        map.put(resource, new Long(modified));
                    }
                } catch (IOException e) {
                    log.error("caught exception", e);
                }
                if (retVal) {
                    if (log.isInfoEnabled()) {
                        log.info("modified files : " + modifiedResources);
                    }
                }
                return retVal;
            }
        };

        timer = new Timer(true);
        resetInterval();

    }

    private Object getParentObject() throws Exception {
        r.lock();
        try {
            return super.getObject();

        } finally {
            r.unlock();
        }
    }

    public SqlSessionFactory getObject() {
        return this.proxy;
    }

    public Class<? extends SqlSessionFactory> getObjectType() {
        return (this.proxy != null ? this.proxy.getClass()
                : SqlSessionFactory.class);
    }

    public boolean isSingleton() {
        return true;
    }

    public void setCheckInterval(int ms) {
        interval = ms;

        if (timer != null) {
            resetInterval();
        }
    }

    private void resetInterval() {
        if (running) {
            timer.cancel();
            running = false;
        }
        if (interval > 0) {
            timer.schedule(task, 0, interval);
            running = true;
        }
    }

    public void destroy() throws Exception {
        timer.cancel();
    }
}
```

## 2. **xml 파일에서 sqlSessionFactory 수정**

### **context-mapper.xml**

기존의 SqlSessionFacotyrBean 객체를 추가된 ***RefreshableSqlSessionFactoryBean***으로 수정

```xml
<bean id="sqlSessionFactory" class="com.mine.RefreshableSqlSessionFactoryBean">
<property name="dataSource" ref="dataSource"/>
<property name="configLocation" value="classpath:/mybatis/mapper-config.xml" />
<property name="interval" value="500" />
<property name="mapperLocations">
  <list>
 	 <value>classpath:/com/mapper/**/*.xml</value>
  </list>
</property>
</bean>

<!-- scan for mappers and let them be autowired -->
<bean class="org.mybatis.spring.mapper.MapperScannerConfigurer">
	<property name="basePackage" value="cbsp" />
</bean>
```