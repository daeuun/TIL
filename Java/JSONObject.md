### JSONObject 

JSON형태의 데이터를 관리해 주는 메서드

맵의 특성으로 인해 순서를 보장하지 않는다.

즉 똑같이 뽑아내도 내용물의 순서가 섞일 수 있다.

```java
JSONObject obj = new JSONObject(); 
obj.put("이름","덩치"); 
obj.put("거주지","서울"); 
String data = obj.toString(); 
System.out.println(data); 
// 결과 {"이름":"덩치","거주지":"서울"} 
```

💡key값에 해당하는 value만 뽑고싶다면‼️

String data = obj.get("key"); 

( String 에 담아준다 )

"key"에 해당하는 벨류를 반환한다.

