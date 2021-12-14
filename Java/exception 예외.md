### 1. 프로그램 오류

1. **컴파일 에러** **(Compile Error)** : 컴파일 시 발생하는 오류
    
    문법오류(Syntax Error)가 해당되고 파싱 에러(Parsing Error)라고도 불림. 
    
    컴파일하는 동안에 발생하기 때문에 다른 스레드에는 전혀 영향 주지 않음.
    
    대부분의 컴파일러 오류는 코드 입력 시의 실수, 개발자의 **문법적인 오류** 때문에 발생
    
    - 컴파일러/인터프리터가 소스 코드를 바이트 코드로 변환하면서 발견한 오류를 출력함
    - 컴파일 시에 에러 메시지로 오류의 위치를 알려주기 때문에 찾기 쉬운 에러임
    - 수정되지 않으면 프로그램은 컴파일되지 않음
2. **런타임 에러 (Runtime Error)** : 컴파일 후 프로그램이 실행(runtime)하는 동안에 발생함.
    
    대부분 try-catch문을 이용해 에러를 해결할 수 있음.
    
3. **논리적 에러 (Logical Errors)** : 컴파일과 실행은 정상적이지만, 예상한 결과대로 안나오는 오류

> **에러 error** : 수습될 수 없는 심각한 오류
                   ⇒ OutOfMemoryError(메모리부족) , StackOverflowError  
**예외 exception** : 개발자가 코드로 수습할 수 있는 오류 ⇒ 예외처리해서 수습가능함
> 

<br>

### 2. 예외 클래스의 계층구조

> **Checked** 컴파일러가 체크함  
**Unchecked**
> 

### RuntimeException (Unchecked Exception)

: 개발자의 실수에 의해 발생할 수 있는 예외들!

- ArrayIndexOutOfBoundsException
    
    ```java
    int[] arr = {0, 1, 2, 3, 4};
    arr[5] = 4; // 배열의 크기보다 크거나 음수 인덱스에 대한 요청에 예외 발생!!
    ```
    
- NullPointerException
    
    null 객체를 생성 후, 인스턴스를 생성하지 않고 null obj를 사용하려 할 경우 발생하는 오류
    
    ```java
    // 초기화 시 null이 없도록 하자
    String str = "";
    String[] arr = new String[0];
    List list = new ArrayList();
    
    // NullPointerException 방지
    Obj a = new Obj();
    String b = a.getResult();
    if(b != null) { // null인 경우를 체크해서 예외발생을 막음
    	b.toString();
    }
    ```
    
- ClassCastException
    
    클래스간에 잘못된 형변환을 했을때 발생하는 오류
    
    하위 호환되지 않는 클래스로 캐스트를 시도할 때 컴파일 오류가 발생한다.
    
    캐스팅은 상속받은 클래스로 업캐스팅 하거나, 상위클래스에서 하위클래스로 다운캐스팅을 해야한다.
    
    ```java
    Object x = new Integer(0);
    System.out.println((String)x); // Integer type을 String으로 형변환 불가
    ```
    
- ArithmeticException
    
    정수를 정수로 나눌때 0으로 나누면 발생하는 오류
    
    ```java
    public class ExceptionExam {
            public static void main(String[] args) {
                int i = 10;
                int j = 0;
                try{
                    int k = i / j;
                    System.out.println(k);
                }catch(ArithmeticException e){ // ArithmeticException예외발생시 실행되는 구문
                    System.out.println("0으로 나눌 수 없습니다. : " + e.toString());
                }finally {
                    System.out.println("오류가 발생하든 안하든 무조건 실행되는 블록입니다.");
                }
            }
        }
    ```
    

<br>

### 3. try-catch문

```java
public class ExceptionExam {
        public static void main(String[] args) {
            
            try{
               // 실행할 코드
            }catch(Exception e){ 
               // 예외발생시 실행되는 구문
            }finally {
                // 오류가 발생하든 안하든 무조건 실행. finally는 꼭 없어도 된다!
            }
        }
    
```

<br>

### 4. try-catch문의 흐름

```java
	
public static void main(String[] args) {
    int i = 5;
		int j = 0;	
		
		try{
      int x = divide(i, j);
			System.out.println(x);
    }catch(ArithmeticException e){ 
       System.out.println("0으로 나눌 수 없습니다.");
    }
}

// 호출하는 메소드에서 thorws로 오류를 받아서 처리하라는 의미
public static int divide(int i, int j) throws ArithmeticException {
	int x = i/j;
	return x;
}
```

<br>

### 5. catch블럭

예외가 발생하면 ⇒ 

발생한 예외에 해당하는 클래스의 *인스턴스가 만들어진다. ⇒ 많이 사용하면 런타임 속도저하 발생함 ⇒ 제어로직에 예외를 사용하지 마라*

```java
catch(예외와 같은 타입의 참조변수 선언하기) {
	// ...
} catch(Exception e) {
	// ...
} catch(ArithmeticException e) {
	// 'Unreachble catch block for ArithmeticException'
	// 여기서 컴파일 오류 발생!! => Exception가 더 부모요소라서
	// 다형성의 개념이 여기서 적용됨
}
```

위에서부터 차례로 내려가며 catch블럭의 ()괄호안에 선언된 인스턴스를 검사함

*instanceof* 연산자를 이용하며 ⇒ true인 catch블럭 만날 때 까지 계속 검사함. 만일 일치하는 catch블럭을 찾는다면 이후의 catch블럭은 검사하지 않음

- 참조변수가 *참조하고 있는 인스턴스의 실제 타입을 알아보기 위해* 사용
- 주로 조건문에 사용
- instanceof의 왼쪽에는 참조변수, 오른쪽에는 타입(클래스명)이 피연산자
- 연산의 결과로 boolean값인 true, false 중의 하나를 반환

### 예외 발생원인 찾기

`printStackTrace() vs. logger.error()` 

printStackTrace()은 예외발생 당시의 호출스택에 있었던 메서드의 정보와 예외메세지 출력

시스템 어딘가에 출력되기 때문에 어디에 출력되는지 잘모름. 시스템 성능저하

- `getMessage()` 발생된 예외클래스의 인스턴스에 저장된 메세지 출력

<br>


### 8. finally블럭

try-catch블럭에 return문이 실행되는 경우에도 반드시 finally가 실행됨

```java

```

<br>

### 9. try-with-resources

*자동 자원 반환*

- Java가 **프로그램 외부에 있는 데이터** File, Network, DB = Resource에 엑세스 하려고 할 때

예외적인 상황이 발생할 수 있음

- resource를 붙잡고 있던 행위를 하다가, 작업이 끝나면 놔줘야 함 ⇒ **close()**

**close()**

- try문에서 예외가 발생하면 바로 catch문로 넘어가게 됨 ⇒ close()를 실행할 수 없게 됨!

그래서 try문 안에 close()를 놓아서는 안된다!

- 이 때 등장한 것이 finally임

따라서 try문에서 Exception이 발생하든 않든 무조건 finally를 거쳐가게 됨

```java
import java.io.FileWriter;
import java.io.IOException;

public class CheckedExceptionApp {
    public static void main(String[] args) {

        FileWriter f = null; // (2) 중괄호 밖에서 f 미리 선언하고 쓰기 => finally안에서 쓰기 위해 미리 선언해둠

        try {
            f = new FileWriter("data.txt");
            f.write("Hello");
            //f.close(); // Unhandled exception: java.io.IOException 여기 예외발생 -> 반드시 예외처리 해줘야 컴파일러가 컴파일해줌!
        }catch (IOException e){
            e.printStackTrace();
        }finally {

            // (3) f가 세팅이 되어있을 때만 실행되게 하도록 하려면?
            // 만약에 f가 null이 아니라면
            if (f != null) {
                try{ // (4) Unhandled exception: java.io.IOException 발생해서 또 예외처리 해줘야함
                    f.close(); // (1) Cannot resolve symbol 'f' {}안에서 선언된 f라서 사용할 수 없다는 뜻
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

```

그래서 모든 catch문에 close() 를 추가하거나, finally문을 만들어서 처리했어야 했다.

**try with resource**

하지만 JDK7 부터 추가된 try with resource를 사용하면 따로 finally 블록이나 모든 catch 블록에 close() 하지 않아도 된다.

- **try에 자원 객체를 전달하면, try 코드 블록이 끝나면 자동으로 자원을 종료해주는 기능**

이러한 구문이 가능하기 위해서는 해당 객체가 `AutoClosable를 impliment`하고 있어야한다.

**AutoCloseable 인터페이스**

```java
/**
 *@authorJosh Bloch
 *@since1.7
 */
public interface AutoCloseable {
    void close() throws Exception;
}
```

최종으로 try-with-resource 적용한 코드

```java
import java.io.FileWriter;
import java.io.IOException;

public class TryWithResource {
    public static void main(String[] args) {

        // try with resource statements
        try (FileWriter f = new FileWriter("data.txt")) { // try문 안에 입력해주면 자동으로 적용됨
            f.write("Hello");
            f.close(); // Redundant 'close()' 자동으로 해줘서 필요없음!!
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
```

<br>

### 12. 연결된 예외 chained exception

여러가지 예외(원인과 결과)를 하나의 큰 분류의 예외로 묶어서 다루기 위해

### Exception Handling **in** Spring MVC

[Exception Handling in Spring MVC](https://spring.io/blog/2013/11/01/exception-handling-in-spring-mvc)

[@ControllerAdvice, @ExceptionHandler를 이용한 예외처리 분리, 통합하기(Spring에서 예외 관리하는 방법, 실무에서는 어떻게?)](https://jeong-pro.tistory.com/195)
