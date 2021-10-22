Java에서 문자열을 다루는 대표적인 클래스로 String, StringBuffer, StringBuilder가 있다.

연산이 많지 않을 때는 특별한 이슈가 발생할 가능성이 거의 없지만, 반복문과 같이 연산 횟수가 많아지거나 멀티스레드, 경쟁 상태(Race Condition) 등의 상황이 자주 발생하면 각 클래스의 특징을 이해하고 상황에 맞는 적절한 클래스를 사용해야 한다.

<br>

## 💡 **String은 immutable하다.**

자바에서 문자열을 다룰 때 사용하는 **String은 불변**이다. 그래서 한 번 생성되면 변경될 수 없다. 

String 객체는 한번 생성되면 **할당된 메모리 공간이 변하지 않는다.**

그래서 문자열을 잇는 .concat() 메서드나 '+' 연산자를 사용해 다른 문자열을 추가할 때 기존 문자열에 새로운 문자열이 추가되는 것이 아니라, 새로운 **String 객체를 만들고 그 객체를 참조**하게 한다. 

메모리 공간 내의 값이 변하는 것이 아니라, **"String Pool"**이라는 공간 안에 메모리를 할당 받아 새로운 String 클래스 객체를 만들어서 문자열을 나타내는 것이다.

즉, String 클래스 객체는 `Heap 메모리 영역`(가비지 컬렉션이 동작하는 영역)에 생성하고 한번 생성된 객체의 내부 내용을 변화 시킬 수 없다.

따라서 레퍼런스가 가리키고 있던 문자열이 다른 문자열로 대체되면, 기존 문자열은 레퍼런스의 참조가 사라져 `Unreachable` 상태가 되어 가비지 컬렉션(Garbage Collection) 대상이 된다.

이러한 이유로 String객체는 연산 한 번 할 때마다 계속해서 문자열 객체를 만드는 오버헤드가 발생해 성능이 좋지 않다.

![Untitled (1)](https://user-images.githubusercontent.com/79685920/138230526-17d3d44e-9b28-47b2-8c6c-ad270b51cd0f.png)

<br>


## 💡 **StringBuilder와 StringBuffer**

`StringBuilder`와 `StringBuffer`의  String과 가장 비교되는 차이점은 가변의 속성을 가지고 있다는 점이다. 이 둘은 문자열을 한 번 만들고 연산이 필요할 때마다 기존의 버퍼 크기를 변경해가며 문자열을 변경한다. 따라서 변경될 때마다 새롭게 객체를 만드는 String 보다 더 빠르다.

<br>

### 그렇다면 **StringBuilder와 StringBuffer의 차이는 무엇일까?**

바로 동기화!

StringBuilder의 경우 동기화를 보장하지 않지만,

StringBuffer는 멀티스레드 환경에서 synchronized키워드가 가능하므로 동기화가 가능하다. 즉, thread-safe하다.

아래 문자열을 추가하는 `append` 메서드 구현을 보면 동기화 여부를 확인할 수 있다.

```java
// StringBuilder
public StringBuilder append(String str) {
    super.append(str);
    return this;
}

// StringBuffer
public synchronized StringBuffer append(String str) {
    super.append(str);
    return this;
}
```

StringBuffer는 각 메서드 별로 Synchronized Keyword가 존재하여, 멀티스레드 환경에서도 동기화를 지원한다. 

멀티스레드 환경이라면 값의 동기화 보장을 위해 StringBuffer를 사용하고, 단일스레드 환경이라면 StringBuilder를 사용하는 것이 좋다. 

![Untitled](https://user-images.githubusercontent.com/79685920/138230570-ab51c1c1-66d5-4f6e-a96b-1fdf61230268.png)
