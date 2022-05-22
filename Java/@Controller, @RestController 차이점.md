### @Controller / @RestController 차이점

초기에는 Spring이 프론트와 백엔드의 역할을 같이하고 있었다.  
그래서 @Controller는 화면에 보여주는 View를 반환하는 기능을 가지고 있었다.  

그렇지만 **Data만 반환**을 해야하는 경우가 점차 많이 생기게 되었다.  
(RestApi 서버인 경우, 화면의 특정 부분만 렌더링 할 경우 등이 있다.)

이럴 때는 데이터만을 반환하기 위해 `@ResponseBody`를 이용하여 *Json 형태* 로 데이터를 반환한다.

그런데 계속해서 @ResponseBody를 붙이는 것에 대한 불편함이 생겼고, 이걸 합친 기능이 만들어지게 된다.  
이것이 바로 @RestController이다. (Spring 4버전 이후로 출시되었다고 한다.)

```
💡 @RestController = @Controller + @ResponseBody
```

메서드의 리턴 타입으로 사용자가 정의한 클래스 타입을 사용할 수 있고, 이를 JSON이나 XML로 자동으로 처리할 수 있다.  
*REST 방식에서 가장 중요한 점은 서버에서 전송하는 것이 순수한 데이터라는 점이다.*   
기존의 Controller에서 Model에 데이터를 담아 JSP 등과 같은 View로 전달하는 방식이 아니므로 기존의 Controller와는 조금 다르게 동작한다.

Json으로의 변환은 Message Converter가 도와준다.  
기본적으로 반환이 Json이기 때문에 반환타입을 지정하는 produces에 `application/json` 설정을 추가하지 않아도 된다.
