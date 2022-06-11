dto 객체는 View layer와 데이터를 주고받을 때 사용된다.  
entity객체는 db layer와 데이터를 주고받을 때 사용된다.

절대로 테이블과 매핑되는 Entity 클래스를 Request/ Response 클래스로 사용해서는 안된다.  
Entity 클래스가 변경되면 여러 클래스에 영향  
Entity 클래스와 Controller에서 쓸 DTO는 분리

