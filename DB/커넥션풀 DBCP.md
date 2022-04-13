### 1. 커넥션 풀(DBCP)이란?

웹 컨테이너(WAS)가 실행되면서 DB와 미리 connection(연결)을 해놓은 객체들을 pool에 저장해두었다가

클라이언트 요청이 오면 connection을 빌려주고, 처리가 끝나면 다시 connection을 반납받아 pool에 저장하는 방식을 말합니다.


### 2. 커넥션 풀(DBCP)을 사용하는 이유
```java
Connection conn = null; 
PreparedStatement pstmt = null; 
ResultSet rs = null; 

try { 

sql = "SELECT * FROM T_BOARD" 

// 1. 드라이버 연결 DB 커넥션 객체를 얻음 
connection = DriverManager.getConnection(DBURL, DBUSER, DBPASSWORD); 

// 2. 쿼리 수행을 위한 PreparedStatement 객체 생성 
pstmt = conn.createStatement(); 

// 3. executeQuery: 쿼리 실행 후 
// ResultSet: DB 레코드 ResultSet에 객체에 담김 
rs = pstmt.executeQuery(sql); 

} catch (Exception e) { 
} finally { 
    conn.close(); 
    pstmt.close(); 
    rs.close(); 
} 
}
```

자바에서 DB에 직접 연결해서 처리하는 경우(JDBC) 드라이버(Driver)를 로드하고 커넥션(connection) 객체를 받아와야 한다.  
그러면 매번 사용자가 요청을 할 때마다 드라이버를 로드하고 커넥션 객체를 생성하여 연결하고 종료하기 때문에 매우 비효율적이다.  
이런 문제를 해결하기 위해서 커넥션풀(DBCP)를 사용한다.


### 여기서 DBCP란?

DataBase Connection Pool 의 약자  
DB와 커넥션을 맺고 있는 객체를 관리하는 역할을 한다.


### 3. 커넥션 풀(DBCP) 특징
+ 웹 컨테이너(WAS)가 실행되면서 connection 객체를 미리 pool에 생성한다.  
+ HTTP 요청에 따라 pool에서 connection객체를 가져다 쓰고 반환한다. 
+ 이와 같은 방식으로 물리적인 데이터베이스 connection(연결) 부하를 줄이고 연결 관리 한다.
+ pool에 미리 connection이 생성되어 있기 때문에 connection을 생성하는 데 드는 요정 마다 연결 시간이 소비되지 않는다.
+ 커넥션을 계속해서 재사용하기 때문에 생성되는 커넥션 수를 제한적으로 설정함


### 동시 접속자가 많을 경우

위에 커넥션 풀 설명에 따르면, 동시 접속 할 경우 pool에서 미리 생성 된 connection을 제공하고 없을 경우는 사용자는 connection이 반환될 때까지 번호순대로 대기상태로 기다린다.

여기서 WAS에서 커넥션 풀을 크게 설정하면 메모리 소모가 큰 대신 많은 사용자가 대기시간이 줄어들고, 반대로 커넥션 풀을 적게 설정하면 그 만큼 대기시간이 길어진다.


### 3. Apache의 Commons DBCP

커넥션 풀에는 Commons DBCP와 Tomcat-JDBC, BoneCP, HikariCP 등이 있는데, 우리는 이미 스프링 웹 프로젝트에서 자신도 모르게 사용하고 있을 수 있다. 본인 같은 경우는 Commons DBCP 라이브러리를 주로 쓰는 것만 봤다.


### Commons DBCP 스프링 설정

Apache Commons DBCP 스프링 설정
```xml
<bean id="dataSource-mysql" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close"> 
    <property name="driverClassName" value="${Globals.DriverClassName}"/> 
    <property name="url" value="${Globals.Url}" /> 
    <property name="username" value="${Globals.UserName}"/> 
    <property name="password" value="${Globals.Password}"/> 
    <property name="maxActive" value="${Globals.maxActive}"/> 
    <property name="maxIdle" value="${Globals.maxIdle}"/> 
    <property name="maxWait" value="${Globals.maxWait}"/> 
</bean> 
```

pom.xml
```xml
<dependency> 
    <groupId>commons-dbcp</groupId> 
    <artifactId>commons-dbcp</artifactId> 
    <version>1.4</version> 
</dependency> 
```

### Commons DBCP 구조
속성 이름 : 설명
initialSizeBasicDataSource : 클래스 생성 후 최초로 getConnection() 메서드를 호출할 때 커넥션 풀에 채워 넣을 커넥션 개수
maxActive : 동시에 사용할 수 있는 최대 커넥션 개수(기본값: 8)
maxIdle : 커넥션 풀에 반납할 때 최대로 유지될 수 있는 커넥션 개수(기본값: 8)
minIdle : 최소한으로 유지할 커넥션 개수(기본값: 0)




커넥션 개수와 관련된 속성은 다음과 같은 조건을 논리적 오류가 없게 설정하는걸 추천한다.

+ maxActive >= initialSize
+ maxIdle >= minIdle
+ maxActive = maxIdle


