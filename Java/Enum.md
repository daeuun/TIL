## Enum

열거형 == **서로 연관된 상수들의 집합**

<br>

## Enum Type

1. 클래스처럼 보이게 하는 상수

2. 서로 관련있는 상수들끼리 모아 상수들을 대표할 수 있는 이름으로 타입을 정의하는 것

3. Enum 클래스 형을 기반으로 한 클래스형 선언

<br>

## Enum 등장 배경

Enum이 등장하기 이전에는 상수값 관리를 정수 열거 패턴 (int enum pattern)으로 사용했다.  
정수 열거 패턴을 사용한 상수값(constant)은 컴파일을 하면 그 값이 클라이언트 파일에 그대로 새겨지므로, 상수 값이 바뀌면 컴파일을 다시 해야한다.

```java
public class Fruits {
  private static final int APPLE = 1;
  private static final int MANGO = 2;
  private static final int BANANA = 3;
}
```
<br>

열거 패턴의 단점을 극복하기 위해 열거 타입(Enum Type)을 사용하게 되었다. 

생성방법은 class 키워드 자리에 enum만 적어주면 된다!!

```java
public enum Fruits {
    APPLE(1),
    MANGO(2),
    BANANA(3);

    private final int fruits;

    Fruits(int fruits) {
        this.fruits = fruits;
    }
}
```

<br>

## **열거형(Enum) 선언 방법**

```java
enum Season { //class 외부에서 선언
    봄, 여름, 가을, 겨울
}

public class enum_ex {
	public enum Season { //class 내부에서 선언
        봄, 여름, 가을, 겨울
    }
}
```

```java
enum Season {
    봄, 여름, 가을, 겨울
}

public class People {
    public String name; // 이름
    public Season favorite_session; // 좋아하는 계절

    public static void main(String[] args) {
    	People people = new People();
        
    	people.name = "이다은";
    	people.favorite_session = Season.봄;
         
        System.out.println("이름 : " + people.name);
        System.out.println("좋아하는 계절 : " + people.favorite_session);
    }
}
```

<br>

## **열거객체 메소드**

### **values()**

열거 타입의 모든 열거객체들을 배열로 만들어 리턴

```java
enum Season {
    봄, 여름, 가을, 겨울
}

public class enum_ex {
    public static void main(String[] args) {
    	for(Season season : Season.values()){
    	    System.out.println(season);
        }
    }
}

/*
봄
여름
가을
겨울
*/
```
<br>

### **ordinal()**

전체 열거 객체 중 몇번째 열거 객체인지 알려줌

열거 객체의 순번은 배열과 마찬가지로 인덱스 0부터 시작

```java
enum Season {
    봄, 여름, 가을, 겨울
}

public class enum_ex {
    public static void main(String[] args) {
    	Season season = Season.여름;
    	System.out.println(season.ordinal());
    }
}

// 1
```
<br>

### **valueOf()**

매개 값으로 주어지는 문자열과 동일한 문자열을 가지는 열거 객체를 리턴

```java
enum Season { //class 외부에서 선언
    봄, 여름, 가을, 겨울
}

public class enum_ex {

    public static void main(String[] args) {
    	Season season = Season.valueOf("가을");
    	System.out.println(season);
    }
}

// 가을
```
<br>

### **name()**

열거 객체가 가지고 있는 문자열을 리턴

이때 리턴되는 문자열은 열거 타입을 정의할때 사용한 상수 이름과 동일하다.

```java
enum Season { //class 외부에서 선언
    봄, 여름, 가을, 겨울
}

public class enum_ex {

    public static void main(String[] args) {
    	Season season = Season.가을;
    	String name = season.name();
    	System.out.println(name);
    }
}

// 가을
```
<br>

### **compareTo()**

매개값으로 주어진 열거 객체를 기준으로 전후로 몇번째 위치하는지 비교

열거객체가 매개값의 열거 객체보다 순번이 빠르다면 음수가, 순번이 늦다면 양수가 리턴

```java
enum Season { //class 외부에서 선언
    봄, 여름, 가을, 겨울
}

public class enum_ex {

    public static void main(String[] args) {
    	Season seson1 = Season.가을;
    	Season seson2 = Season.겨울;

    	int result1 = seson1.compareTo(seson2);
    	int result2 = seson2.compareTo(seson1);

    	System.out.println("result1 = " +result1);
    	System.out.println("result2 = " +result2);
    }
}

// result1 = -1
// result2 = 1
```