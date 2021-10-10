## Static
static 멤버들은 클래스에 고정된 멤버로 클래스 로더가 클래스를 로딩하면 메모리에 할당 시켜준다.  
⇒ 클래스 로딩이 끝나면 객체를 생성하지 않고 바로 사용할 수 있다.

Class는 Static 영역에 생성되고, new 연산을 통해 생성한 객체는 Heap영역에 생성

- 객체의 생성시에 할당된 Heap영역의 메모리는 Garbage Collector를 통해 수시로 관리를 받는다.
- Static 키워드를 통해 Static 영역에 할당된 메모리는 모든 객체가 공유하는 메모리
    - Garbage Collector의 관리 영역 밖에 존재함  
    ⇒ Static을 자주 사용하면 프로그램의 종료시까지 메모리가 할당된 채로 존재하므로 자주 사용하게 되면 시스템의 퍼포먼스에 악영향을 줌

<br>

### 💡Static 변수 특징

- Static 변수는 클래스 변수이다.
- Static 메모리 영역에 존재하므로 객체가 생성되기 이전에 이미 할당이 되어 있기 때문에 객체를 생성하지 않고도 Static 자원에 접근이 가능하다.

```java
public class Counter  {
	static int count = 0;
	Counter() {
        this.count++;
        System.out.println(this.count);
	}

	public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
    }
}
```

`int count = 0` 앞에 static 키워드를 붙였더니 count 값이 공유되어 다음과 같이 방문자수가 증가된 결과값이 나오게 되었다.

```java
1
2
```

보통 변수의 static 키워드는 프로그래밍 시 메모리의 효율보다는 두번째 처럼 공유하기 위한 용도로 훨씬 더 많이 사용하게 된다.