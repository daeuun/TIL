## **SqlSessionFactory**  
SqlSession을 만드는 객체. 
DB와 MyBatis를 연결해주는 역할  
이 객체가 DataSource를 참조하여 MyBatis와 서버를 연동시켜준다.

### **SqlSession**  
실제 SQL을 날린다.


## **context-mapper.xml**
SqlSessionFactoryBean class를 사용해 SqlSessionFactory를 생성해준다.

```xml
<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
    <property name="dataSource" ref="dataSource"></property>
    <!-- config파일 설정하기 위해 configLocation 속성 추가 -->
    <property name="configLocation" value="classpath:/mybatis-config.xml"></property>
</bean>
```


<br>

### mybatis-config.xml 
이 파일을 통해서 별도의 설정 파일로 mybatis 환경을 설정할 수 있다. 

```java
/* 파일위치 : src/main/resources/mybatis-config.xml */

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="com/atoz_develop/spms/dao/db.properties"/>
 
    <typeAliases>
        <typeAlias type="com.atoz_develop.spms.vo.Project" alias="project"/>
        <typeAlias type="com.atoz_develop.spms.vo.Member" alias="member"/>
    </typeAliases>
 
    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}"/>
                <property name="url" value="${url}"/>
                <property name="username" value="${username}"/>
                <property name="password" value="${password}"/>
            </dataSource>
        </environment>
    </environments>
 
    <mappers>
        <mapper resource="com/atoz_develop/spms/dao/MySqlProjectDao.xml"/>
    </mappers>
</configuration>

```
