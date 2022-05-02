
ajax 사용하던 중 한가지 의문이 생겼습니다.  
왜 JSON.stringify를 사용하지 않고 데이터를 보내면 왜 스프링 @RequestBody에서 받질 못하는지 말입니다. 


그래서 data : {test : "test"} 와 data : JSON.stringify의 타입들을 콘솔로 찍어본 결과 
전자는 object이고 후자는 string이였습니다.



### 차이점

JS Object는 JS Engine 메모리 안에 있는 데이터 구조이고, JSON은 객체의 내용을 기술하기 위한 text 파일이라는 점이 다릅니다.  
JSON은 "파일"이므로 확장자 명이 .JSON인 파일이 존재합니다.

 

이 메서드를 통해 제가 말하고 싶은 것은 JS Object로 HTTP 통신하는 것이 아니라, JSON으로 서버와 클라이언트가 데이터를 주고 받는다는 것입니다.

 

우리가 HTTP 통신을 할 때에는 JS Object가 아닌 JSON으로 서버와 클라이언트 데이터를 주고받는 것이기 때문에 프론트엔드에서 JSON 데이터를 가공하기 위해서는 JS Object로 변경해주는 메소드가 필요한 것이고, 또한 백엔드에 JSON 양식으로 데이터를 보내기 위해서도 메소드가 필요한 것이죠.

 

+ JSON을 JS Object로 파싱하기 위해서 필요한 메소드가 JSON.parse() 메소드라는 것이구요.
+ JS Object를 JSON으로 변환해주기 위해 필요한 것이 JSON.stringify() 메소드가 되는 것입니다.

