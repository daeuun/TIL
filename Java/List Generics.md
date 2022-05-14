### 제네릭스(Generics)
제네릭스를 이용하면 좀 더 명확한 타입체크가 가능해 진다.
우선 제네릭스를 사용하지 않은 경우

```java
ArrayList pitches = new ArrayList();
aList.add("138");
aList.add("129");
String one = (String) pitches.get(0);
String two = (String) pitches.get(1);
```

위처럼 제네릭스를 사용하지 않을 경우에는 ArrayList 안에 추가되는 객체는 Object 자료형
으로 인식된다.   
Object 자료형은 모든 객체가 상속하고 있는 가장 기본적인 자료형이다.
따라서 ArrayList 객체인 pitches에 값을 넣을 때는 문제가 안되지만
값을 가져올 경우에는 항상 Object 자료형에서 String 자료형으로 다음과 같이 형변환
(casting)을 해 주어야만 한다.

```java
String one = (String) pitches.get(0); // Object 자료형을 String 자료형으로 캐스팅한다.
```

또 한가지 주의할 점은 pitches 안에는 String 객체 이외의 객체도 넣을 수 있기 때문에 형 변
환 과정에서 잘못된 형변환으로 인한 오류가 발생할 가능성이 있다는 점이다. 
바로 이러한
 점이 제네릭스의 탄생 이유이기도 하다.

제네릭스를 사용하여 변경
```
ArrayList<String> pitches = new ArrayList<>();
aList.add("138");
aList.add("129");
String one = pitches.get(0); // 형 변환이 필요없다.
String two = pitches.get(1); // 형 변환이 필요없다.
```

제네릭스로 자료형을 선언하기만 하면 그 이후로는 자료형에 대한 형변환 과정이 필요없다.
이미 컴파일러가 pitches에는 반드시 String 자료형만 추가 되어야 함을 알기 때문이다.
이렇게 제네릭스를 이용하면 형변환에 의한 불필요한 코딩과 잘못된 형변환에 의한 런타임
오류를 방지할 수 있다.
