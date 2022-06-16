## **싱글톤 패턴 (singleton pattern)**

static에 대한 개념이 생겼기 때문에 싱글톤을 이해하는것이 어렵지 않다. 

싱글톤은 단 하나의 객체만을 생성하게 강제하는 패턴이다. 

즉 클래스를 통해 생성할 수 있는 객체는 Only One, 즉, 한 개만 되도록 만드는 것이 싱글톤이다.

애플리케이션이 시작될 때 어떤 클래스가 최초 한번만 메모리를 할당하고(Static) 그 메모리에 인스턴스를 만들어 사용하는 디자인패턴.

생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나고 최초 생성 이후에 호출된 생성자는 최초에 생성한 객체를 반환한다. 
(자바에선 생성자를 private로 선언해서 생성 불가하게 하고 getInstance()로 받음)

⇒ 싱글톤 패턴은 단 하나의 인스턴스를 생성해 사용하는 디자인 패턴이다.

(인스턴스가 필요 할 때 똑같은 인스턴스를 만들어 내는 것이 아니라, 동일(기존) 인스턴스를 사용하게함)

```java
class Singleton {
    private Singleton() {}
}

public class Sample {
    public static void main(String[] args) {
        Singleton singleton = new Singleton();  // 컴파일 오류가 발생한다.
    }
}
```

위와 같은 코드를 작성하면 컴파일 에러가 발생한다. 

왜냐하면 Singleton 클래스의 생성자에 `private` 키워드로 다른 클래스에서 Singleton 클래스의 생성자로의 접근을 막았기 때문이다. 이렇게 생성자를 private 으로 만들어 버리면 다른 클래스에서 Singleton 클래스를 `new` 를 이용하여 생성할 수 없게 된다.

`new`를 이용하여 무수히 많은 객체를 생성한다면 싱글톤의 정의에 어긋나지 않겠는가? 

그래서 일단 `new`로 객체를 생성할 수 없도록 막은 것이다.

그렇다면 Singletone 클래스의 객체는 어떻게 생성할 수 있을까? 

```java
class Singleton{

    private Singleton(){
    }

    public static Singleton getInstance(){
	        return new Singleton(); // 같은 클래스이므로 생성자 호출이 가능하다.
    }
}

public class Sample {
    public static void main(String[] args) {
        Singleton singleton =Singleton.getInstance();
				//getInstance()를 호출 할 때마다 계속 새로운 객체가 생성된다. => 싱글돈이 아님!
    }
}
```

위와 같이 코드를 변경하면 이제 getInstance라는 static 메소드를 이용하여 Singleton 클래스의 객체를 생성할수 있다. 

하지만 getInstance를 호출 할 때마다 새로운 객체가 생성된다. 그렇다면 싱글톤이 아니다. 

```java
class Singleton {
    private static Singleton one;
    private Singleton(){
    }

    public static Singleton getInstance() {
        if(one == null) {
            one = new Singleton();
        }
        return one;
    }
}

public class Sample {
		public static void main(String[] args) {
        Singleton singleton1 = Singleton.getInstance();
        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton1 == singleton2);  // true 출력
		    }
}
```

Singleton 클래스에 
one 이라는 static 변수를 두고 
getInstance 메소드에서 one 값이 ***null 인 경우에만 객체를 생성하도록*** 하여 
one 객체가 ***딱 한번만*** 만들어지도록 했다.

getInstance 메소드의 동작원리를 살펴보자.

최초 getInstance가 호출 되면 one이 null이므로 `new`에 의해서 객체가 생성이 된다. 

***이렇게 한번 생성이 되면 one은 static 변수이기 때문에 그 이후로는 null이 아니게 된다.*** 

그런 후에 다시 getInstance 메소드가 호출되면 이제 one은 null이 아니므로 ***이미 만들어진 싱글톤 객체인 one을 항상 리턴한다.***

main 메소드에서 getInstance를 두번 호출하여 각각 얻은 객체가 같은 객체인지 조사 해 보았다. 

역시 예상대로 "true"가 출력되어 같은 객체임을 확인 할 수 있다.

싱글톤 패턴은 static에 대한 이해만 있다면 참 알기쉬운 패턴 중 하나이다.

> ※ 위에서 예제로 든 싱글톤은 Thread Safe 하지는 않다.
> 

### 멀티쓰레드에서 안전한(Thread-safe) 싱글톤 클래스, 인스턴스 만드는 방법

1. Thread safe Lazy initialization (게으른 초기화)

```java
public class ThreadSafeLazyInitialization{

    private static ThreadSafeLazyInitialization instance;

    private ThreadSafeLazyInitialization(){}

    public static synchronized ThreadSafeLazyInitialization getInstance(){
        if(instance == null){
            instance = new ThreadSafeLazyInitialization();
        }
        return instance;
    }

}
```

private static으로 인스턴스 변수를 만들고 private 생성자로 외부에서 생성을 막았으며 

synchronized 키워드를 사용해서 thread-safe하게 만들었다.

하지만 synchronized 특성상 비교적 큰 성능저하가 발생하므로 권장하지 않는 방법이다.

2. Thread safe lazy initialization + Double-checked locking

- 게으른 초기화의 성능저하를 완화시키는 방법

```java
public class ThreadSafeLazyInitialization {

    private volatile static ThreadSafeLazyInitialization instance;

    private ThreadSafeLazyInitialization(){}

    public static ThreadSafeLazyInitialization getInstance(){

        if(instance == null){
            synchronized (ThreadSafeLazyInitialization.class) {
                if(instance == null)
                    instance = new ThreadSafeLazyInitialization();
            }

        }
        return instance;
    }
}
```

getInstance()에 synchronized를 사용하는 것이 아니라, 

첫 번째 if문으로 인스턴스의 존재여부를 체크하고 두 번째 if문에서 다시 한번 체크할 때 동기화 시켜서 인스턴스를 생성하므로 thread-safe하다.

처음 생성 이후에 synchonized 블럭을 타지 않기 때문에 성능저하를 완화했다.

그러나 완벽한 방법은 아니다.

* 가장 많이 사용하고 일반적인 Singleton 클래스 사용 방법

3. Initialization on demand holder idiom (holder에 의한 초기화)

클래스안에 클래스(Holder)를 두어 JVM의 Class loader 매커니즘과 Class가 로드되는 시점을 이용한 방법

```java
public class Something {
    private Something() {
    }

    private static class LazyHolder {
        public static final Something INSTANCE = new Something();
    }

    public static Something getInstance() {
        return LazyHolder.INSTANCE;
    }
}
```

JVM의 클래스 초기화 과정에서 보장되는 원자적 특성을 이용하여 싱글턴의 초기화 문제에 대한 책임을 JVM에 떠넘긴다.

Holder(클래스)안에 선언된 ***`INSTANCE`***가 `static`이기 때문에 클래스 로딩시점에 한번만 호출될 것이며,

`final`을 사용해 다시 값이 할당되지 않도록 만든 방법.
