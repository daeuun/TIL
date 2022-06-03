### **HttpEntity ?**

Spring Framework에서 제공하는 클래스 중 **HttpEntity**라는 클래스가 존재한다.

1. **HttpStatus**
2. **HTTP 요청(Request)** 또는 **응답(Response)**에 대한 요구사항이 있는 ***HttpHeader***와 
3. 내용이 적혀있는 ***HttpBody***를 포함하는 클래스이다.

Response header 에는 웹서버가 웹브라우저에 응답하는 메시지가 들어있고, Reponse body에 데이터 값이 들어가 있다.

HttpEntity 클래스를 상속받아 구현한 클래스가 **RequestEntity**, **ResponseEntity** 클래스이다.

```java
public class RequestEntity<T> extends HttpEntity<T>
public class ResponseEntity<T> extends HttpEntity<T>
```

### Class ResponseEntity<T>

사용자의 HttpRequest에 대한 응답 데이터를 포함하는 클래스이다.

따라서 ***HttpStatus, HttpHeaders, HttpBody*** 를 포함한다.

RestTemplate(서버와 서버간 통신을 쉽게해줌) 및 @Controller메서드에 사용하고 있다.

### RestTemplate

getForEntity()와 exchange() 메서드의 응답객체로도 사용한다.

```java
ResponseEntity<String> entity = template.getForEntity("https://example.com", String.class);
 String body = entity.getBody();
 MediaType contentType = entity.getHeaders().getContentType();
 HttpStatus statusCode = entity.getStatusCode();
```

| 메서드 | HTTP | 설명 |
| --- | --- | --- |
| getForEntity() : ResponseEntity | GET  | GET 방식 요청으로 결과를 ResponseEntity로 반환 |

HTTP 응답에 대한 추가 정보를 담고 있어서 GET 요청에 대한 응답 코드, 실제 데이터를 확인할 수 있다. 

또한 ResponseEntity<T> 제네릭 타입에 따라서 응답을 String이나 Object 객체로 받을 수 있다.

### Controller

메서드의 return value로 객체 자체를 사용한다.

```java
return new ResponseEntity<>(
                ApiResponse.response(
                        HttpStatusCode.OK,
                        HttpResponseMessage.GET_SUCCESS,
                        responseDto), HttpStatus.OK
        );
```

클라이언트에게 응답을 보낸다.

```java
@RestController
public class UserController {
    private UserDaoService userDaoService;

    public UserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<Message> findById(@PathVariable int id) {
        User user = userDaoService.findOne(id);
        Message message = new Message();
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        message.setStatus(StatusEnum.OK); // 상태코드
        message.setMessage("성공 코드"); // 메세지
        message.setData(user); // 데이터

        return new ResponseEntity<>(message, headers, HttpStatus.OK);
    }
}
```
