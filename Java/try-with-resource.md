<img src="https://user-images.githubusercontent.com/79685920/136811081-cda8cb58-e50c-4cba-814d-54aec7e889e2.png" width="550" height="300"/>

- Java가 **프로그램 외부에 있는 데이터** File, Network, DB = Resource에 엑세스 하려고 할 때  
    예외적인 상황이 발생할 수 있음
    
- resource를 붙잡고 있던 행위를 하다가, 작업이 끝나면 놔줘야 함 ⇒ **close()** 

<br>

## 🍀 close()

- try문에서 예외가 발생하면 바로 catch문로 넘어가게 됨 ⇒ close()를 실행할 수 없게 됨!  
    그래서 try문 안에 close()를 놓아서는 안된다!
    
- 이 때 등장한 것이 finally임  
    따라서 try문에서 Exception이 발생하든 않든 무조건 finally를 거쳐가게 됨
    

```java
import java.io.FileWriter;
import java.io.IOException;

public class CheckedExceptionApp {
    public static void main(String[] args) {

        FileWriter f = null; // (2) 중괄호 밖에서 f 미리 선언하고 쓰기

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
}
```

그래서 모든 catch문에 close() 를 추가하거나, finally문을 만들어서 처리했어야 했다.

<br>


## 🍀 try with resource
하지만 JDK7 부터 추가된 try with resource를 사용하면 따로 finally 블록이나 모든 catch 블록에 close() 하지 않아도 된다.  
**try에 자원 객체를 전달하면, try 코드 블록이 끝나면 자동으로 자원을 종료해주는 기능**


이러한 구문이 가능하기 위해서는 해당 객체가 `AutoClosable를 impliment`하고 있어야한다.

<br>

### AutoCloseable 인터페이스

```java
/**
 *@authorJosh Bloch
 *@since1.7
 */
public interface AutoCloseable {
    void close() throws Exception;
}
```
<br>
최종으로 try with resource 적용한 코드  

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