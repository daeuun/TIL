### @ReponseBody 

HttpMessageConverter를 통해서 응답 값을 자동으로 json으로 직렬화 한 뒤 응답해주는 역할

> @RestController = @Controller + @ReponseBody


예외처리 후 json으로 응답하는 방법은 다음과 같습니다.

1. @ControllerAdvice + ReponseEntity 
2. @ReponseContrllerAdvice + 응답객체 + @ResponseStatus 

2번 같은 경우 @ResponseStatus가 추가로 붙습니다.  
이유는 @ResponseStatus를 사용하지 않으면 기본적으로 HttpStatusCode는200으로 응답하기 때문.  
즉, ReponseEntity를 이용하면 자동으로 HttpMessageConverter를 통해서 Json으로 응답하고,응답으로 HttpStatusCode를 전달하기 때문에 @ResponseStatus를 사용하지 않아도 됩니다.



