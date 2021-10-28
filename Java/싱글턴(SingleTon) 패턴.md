# 💡싱글턴 패턴

```java
public class Person { 

	private static Person instance; 

	public static Person getInstance() { 
		if (instance == null) { 
			instance = new Person(); 
		} 
		return instance; 
	} 
}
```

애플리케이션이 시작될 때 어떤 클래스가 **최초 한번만** 메모리를 할당하고(Static) 그 메모리에 인스턴스를 만들어 사용하는 디자인 패턴

생성자가 여러 차례 호출되더라도 실제로 생성되는 객체는 하나고 최초 생성 이후에 호출된 생성자는 최초에 생성한 객체를 반환한다. 

(자바에선 생성자를 private로 선언해서 생성 불가하게 하고 getInstance()로 받아쓰기도 함)

⇒ 싱글턴 패턴은 `인스턴스가 오직 1개만 생성`하는 클래스  

인스턴스가 필요 할 때 똑같은 인스턴스를 만들어 내는 것이 아니라, 동일(기존) 인스턴스를 사용하게함  

### why?
시스템 런타임, 환경 세팅에 대한 정보 등 인스턴스가 여러개일 때 문제가 생길 수 있는 경우가 있다.  

레지스트리 같은 설정 파일의 경우 객체가 여러개 생성되면 설정 값이 변경될 위험이 생길 수 있다.

커넥션 풀, 스레드 풀, 디바이스 설정 객체 등 인스턴스를 여러개 만들게 되면 자원을 낭비하거나 버그를 발생시킬 수 있으므로 오직 하나의 인스턴스만 생성하고 사용하도록 하는 패턴이다.

인스턴스가 1개만 생성되는 싱글턴 패턴을 이용하면, 하나의 인스턴스를 메모리에 등록해서 여러 스레드가 동시에 해당 인스턴스를 공유하여 사용할 수 있으므로, 요청이 많은 곳에서 사용하면 효율을 높일 수 있다.

싱글턴을 만들때 `동시성(Concurrency)` 문제를 고려해서 싱글턴을 설계해야 한다.

<br>

### 싱글턴 패턴의 문제점

- private 생성자를 갖고 있어 상속이 불가능하다.
- 테스트하기 힘들다.
- 서버 환경에서는 싱글톤이 1개만 생성됨을 보장하지 못한다.
- 전역 상태를 만들 수 있기 때문에 바람직하지 못하다.

따라서 Spring에서는 컨테이너를 통해 직접 객체(빈)들을 싱글톤으로 관리함으로써 객체를 재사용함과 동시에 객체지향 개발을 할 수 있도록 한다.

<br><br>

# 💡**자바의 싱글턴 패턴(Sigleton Pattern in Java)**

싱글턴 패턴의 공통적인 특징은 `private constructor`를 가지고, `static method`를 사용하는 것이다.

하나의 인스턴스만을 유지하기 위해 인스턴스 생성에 제약을 둬야한다.

new를 실행할 수 없도록 생성자에 private 접근 제어자를 지정하고, 유일한 단일 객체를 반환할 수 있도록 정적 메소드를 지원해야 한다. 또한 유일한 단일 객체를 참조할 정적 참조변수가 필요하다.

<br>

### 1. **Eager Initialization(이른 초기화, Thread-safe)**

static 키워드의 특징을 이용해서 클래스 로더가 초기화 하는 시점에서 `정적 바인딩(static binding)`(컴파일 시점에서 성격이 결정됨)을 통해 해당 인스턴스를 메모리에 등록해서 사용한다.

이른 초기화 방식은 클래스 로더에 의해 클래스가 최초로 로딩 될 때 객체가 생성되기때문에 Thread-safe함

싱글턴 구현 시 멀티 스레딩 환경에서도 동작 가능하게끔 구현해야 한다. 즉, `Thread-safe` 가 보장되어야 한다.

<br>

### 2. Lazy Initialization with synchronized (동기화 블럭, Thread-safe)

`synchronized` 키워드를 이용한 게으른 초기화 방식

메서드에 동기화 블럭을 지정해서 Thread-safe 를 보장함

컴파일 시점에 인스턴스를 생성하는 것이 아니라, 인스턴스가 필요한 시점에 요청 하여 `동적 바인딩(dynamic binding)`(런타임시에 성격이 결정됨)을 통해 인스턴스를 생성한다.

<br>

### 3. **Lazy Initialization. Double Checking Locking(DCL, Thread-safe)**

동기화 블럭 방식을 개선한 방식이 `DCL(Double Checking Locking)` 방식

인스턴스가 생성되지 않은 경우에만 동기화 블럭이 실행되게 구현한다.

`volatile` 키워드를 사용하면 멀티스레딩을 쓰더라도 uniqueInstance 변수가 Sigleton 인스턴스로 초기화 되는 과정이 올바르게 진행되도록 할 수 있다.

<br>

### 4. **Lazy Initailization. Enum(열거 상수 클래스, Thread-safe)**

Enum 인스턴스의 생성은 기본적으로 Thread-safe 하기 때문에 스레드 관련된 코드가 없어져서 코드가 간단하다. 

하지만 Enum 내의 다른 메서드가 있는 경우에 해당 메서드가 Thread-safe 한지는 개발자가 책임져야한다.

Enum 방식을 사용한 장점은 아주 복잡한 직렬화 상황이나, 리플렉션 공격에도 제 2의 인스턴스가 생성되는 것을 막아준다. 단, 만들려는 싱글턴이 Enum 외의 클래스를 상속해야 하는 경우에는 사용할 수 없다. 또한, Android 같이 Context 의존성이 있는 환경일 경우, 싱글턴의 초기화 과정에 Context 라는 의존성이 끼어들 가능성이 있다.

<br>

### 5. **Lazy Initialization. LazyHolder(게으른 홀더, Thread-safe)**

가장 많이 사용되는 싱글턴 구현 방식

`volatile`이나 `synchronized` 키워드 없이도 동시성 문제를 해결하기 때문에 성능이 뛰어남

싱글턴 클래스에는 InnerInstanceClass 클래스의 변수가 없기 때문에, static 멤버 클래스더라도, 클래스 로더가 초기화 과정을 진행할때 InnerInstanceClass 메서드를 초기화 하지 않고, getInstance() 메서드를 호출할때 초기화된다. 즉, `동적바인딩(Dynamic Binding)` (런타임시에 성격이 결정) 의 특징을 이용하여 Thread-safe 하면서 성능이 뛰어나다.

InnerInstanceClass 내부 인스턴스는 static 이기 때문에 클래스 로딩 시점에 한번만 호출된다는 점을 이용한 것이며, final을 써서 다시 값이 할당되지 않도록 한다.
