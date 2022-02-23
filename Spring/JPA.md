## ORM(Object-relational mapping) 이란
Object-relational mapping (객체 관계 매핑).  
객체는 객체대로 설계하고, 관계형 데이터베이스는 관계형 데이터베이스대로 설계한다.  
ORM 프레임워크가 중간에서 매핑해준다.
대중적인 언어에는 대부분 ORM 기술이 존재한다.
ORM은 객체와 RDB 두 기둥 위에 있는 기술 이다.

<br>

## JPA(Java Persistence API) 란
현재 자바 진영의 ORM 기술 표준으로, 인터페이스의 모음이다.   
즉, 실제로 동작하는 것이 아니다.  
JPA 인터페이스를 구현한 대표적인 오픈소스가 Hibernate라고 할 수 있다.  
JPA 2.1 표준 명세를 구현한 3가지 구현체: Hibernate, EclipseLink, DataNucleus

+ 버전  
JPA 1.0(JSR 220) 2006년 : 초기 버전. 복합 키와 연관관계 기능이 부족  
JPA 2.0(JSR 317) 2009년 : 대부분의 ORM 기능을 포함, JPA Criteria 추가  
JPA 2.1(JSR 338) 2013년 : 스토어드 프로시저 접근, 컨버터(Converter), 엔티티 그래프 기능이 추가  

### * EJB
과거의 자바 표준 (Entity Bean). 
과거의 ORM  
+ 문제?
1. 코드가 매우 지저분하다. 
2. API의 복잡성이 높다. (interface를 많이 구현해야 함)
3. 속도가 느리다.

### * Hibernate
ORM 프레임워크, Open Source SW. 
‘Gavin King’ 과 시러스 테크놀로지스 출신 동료들이 EJB2 스타일의 Entity Beans 이용을 대체할 목적으로 개발하였다.

<br>

## JPA의 동작 과정
JPA는 애플리케이션과 JDBC 사이에서 동작한다.  
개발자가 JPA를 사용하면, JPA 내부에서 JDBC API를 사용하여 SQL을 호출하여 DB와 통신한다.  
즉, 개발자가 직접 JDBC API를 쓰는 것이 아니다.
