### (1) Java Beans

- 일반적으로 자바빈은 속성과, 그 속성에 대한 getter, setter 메서드로 구성된 데이터 객체(VO, Value Object)를 말하며
- 데이터 전송에 사용되는 객체를 말한다.
- 자바로 작성된 재사용 가능한 소프트웨어 컴포넌트
- getter, setter 메서드를 통해 컴포넌트에 접근가능

### (2) VO; Value Object

- 데이터 그 자체로 의미 있는 것을 담고 있는 객체
- DTO와 같은 개념이나 Read-Only 속성 객체
- 간단한 독립체(Entity)를 의미하는 작은 객체를 의미한다.
- 특정 비즈니스 값을 담는 객체
- 모든 속성 값이 같다면 같은 객체임을 의미
    
    (ex: 지폐에는 각 고유번호가 존재한다. 하지만 우리는 만원짜리 두 장을 보면 만원이 2장 있다고 인식하지 고유번호 A인 지폐 하나, 고유번호 B인 지폐 하나로 인식하지 않는다.)
    
- 생성자 외에 setter 속성을 띄는 메소드 선언 금지

### (3) DTO; Data Transfer Object

- 계층간 데이터 교환을 위한 객체
- 일반적인 DTO는 로직을 가지고 있지 않다. 순수한 데이터 객체이며 속성과 속성에 접근하기 위한 getter, setter메서드로만 구성되는 POJO
(** POJO: 특정 인터페이스 또는 클래스를 상속하지 않는 일반 자바 개체)
- request와 response용 DTO는 view를 위한 클래스

### (4) MAP<String, Object>

```java
Map<String, Object>() map = new HashMap<String,Object>();
map.put("fileName", "History_20220523");
map.put("title", "report");
```

- key 값을 String으로 value 값을 Object 형으로 put 메소드를 통해 입력할 수 있다.
- List형 데이터(Json 형태)를 Map에 넣기 위해 `Map<String, Object>`를 사용한다.

```java
{
	{key : "userid",
	value : {"aa1","bb2","cc3","dd4","ee5","ff6"}},
	{key : "password",
	value : {"pass","word"}},
	...
};
```

- LinkedHashMap
    
    : put을 통해 입력된 순서대로 Key가 보장된다.
    

계속해서 Map형태가 나오길래 왜 쓰는건지 궁금해져서 검색해봤는데 이게 아주 오래전부터 논쟁해온 이야기였다...?!

*아직도 완벽하게 이해되지는 않지만, 데이터베이스 SQL 위주로 돌아가는 환경에서 계속해서 DB 테이블의 요구사항이 바뀌면 반영하기 힘들기 때문에 유지보수에 있어서 편리함을 위해 Map을 사용하는 것 같다…*
