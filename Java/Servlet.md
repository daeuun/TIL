### **1. Servlet**

정확히 말하면 **'Java Servlet'**을 의미하는데, 

**자바를 사용하여 동적으로 웹 페이지를 만들어주는 서버 측 프로그램 혹은 사양**을 의미한다. 

일반적으로 불리는 웹 서버(Web Server)는 클라이언트의 요청에 정적인 페이지(Javascript, HTML, 이미지)만을 응답해 줄 수 있다. 그래서 웹 서버 자체만으로는 동적 페이지를 클라이언트에게 전달할 수 없으며, 이는 필히 다른 애플리케이션의 도움을 필요로 한다.

이 역할을 하는 애플리케이션이 '(Java) Servlet'이다.

![https://blog.kakaocdn.net/dn/dwijpz/btrdrlqcKkl/I5h53g47fNGBQ9oKjk8wkK/img.png](https://blog.kakaocdn.net/dn/dwijpz/btrdrlqcKkl/I5h53g47fNGBQ9oKjk8wkK/img.png)

<br>

### **2. Servlet Container**

그러나 Servlet 자체도 혼자서는 그 역할을 오롯이 해낼 수 없다.

클라이언트의 요청에 응답하기 위해서는 여러 개의 스레드를 생성해야 하며, 부가적으로는 서블릿(서비스) 메서드 요청, 파라미터 관리 등의 일을 해야 한다.

그래서 보통은 웹 서버와 Servlet 사이에 `Servlet Container`를 두어 ***클라이언트 요청에 대한 스레드 풀(Thread Pool)을 관리*** 하게 된다. 

Servlet Container는 웹 서버의 요청이 있을 때마다 스레드를 생성하며, 요청에 맞는 Servlet 메서드를 호출하여 웹 서버로 다시 전달한다.

대표적으로 WAS(Web Application Server)로 알려진 Apache Tomcat이 있지만, 사실 WAS는 (Web Server + Servlet Container) 두 개가 합쳐진 좀 더 넓은 개념이다.

<br>

### **3. HttpServeletRequest Interface?**

1. **인터페이스의 기능**
    
    서블릿 컨테이너는 웹 서버로부터 들어온 클라이언트의 요청을 넘겨받아, HttpServletRequest 인터페이스의 객체를 생성한다. 이 객체에 클라이언트의 요청사항들을 담아 서블릿 서비스 메서드에 전달하므로, 클라이언트의 요청에 관한 많은 정보를 내장시키고 있다.
    
2. **인터페이스의 내장 메서드**
    
    HttpServeletRequest 인터페이스는 내부적으로 ServletRequest 인터페이스를 상속받고 있다.
    
    따라서, 아래의 내장 메서드들은 ServletRequest 혹은 HttpServeletRequest 인터페이스의 멤버이다.

<br>    

### ****4. 활용 방안****

1. **Log를 남기는데 활용**
    
    로그인한 사용자 정보, 클라이언트의 ip 주소, 접근 메서드(request.getRequestURI) 정도를 별도의 log 테이블로 구성한다. 
