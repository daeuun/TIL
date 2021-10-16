`상수/리터럴 : 둘 다 변하지 않는 값(데이터)을 의미`

<br>

## **1. 변수**

- 하나의 값(리터럴)을 저장하기 위한 공간
- 메모리에 값을 기록하기 위한 공간

<br>

## **2. 상수(Constant)**

**한번만 값을 저장가능한** (**변하지 않는) 변수**

- 상수에 넣는 데이터로는 숫자만 오는 것이 아니라, 클래스나 구조체 같은 객체도 올 수 있다.
- 참조변수를 상수로 지정할 때, 참조변수 안의 속성의 데이터까지도 변하지 않는다고 생각할 수 있다. 하지만 참조변수 메모리의 주소값이 변하지 않는다는 의미일 뿐, **그 주소가 가리키는 데이터들은 변할 수 있다.**
- 프로그래밍에서 상수를 쓸때는 C, C++ ,C#은 const , Java는 final 제어자를 쓴다.
    
    ```java
    final Test t1 = new Test();
    
    t1 = new Test(); // 불가능
    t1.num = 10;     // 가능 : 클래스 안의 데이터를 변경해도 상관이 없다
    ```
    
    ```java
    const a = { name: "daeun", age: 20 };
    
    a = [ apple, banana ]; // 불가능
    a.age = 10; // 가능
    ```
    
<br>

## **3. 리터럴(Literal) (값)**

**데이터 그 자체**

**변수에 넣는 변하지 않는 데이터**를 의미한다.

- 프로그램 상에 필요한 명시적인 값
- 사용자가 키보드로 입력한 값 / 마우스로 클릭한 값

```java
int a = 1;
```

int 앞에 final을 붙이면 a는 상수가 된다. 여기서의 리터럴은 1이다.

즉, 1과 같이 변하지 않는 데이터(boolean, char, double, long, int...)를 리터럴(literal)이라고 부른다.

<br>

### 💡 정리!!

```java
int num = 100;
final int Max = 100;

// num = 변수
// Max = 상수
// 100 = 리터럴
```

<br>

## **3. 리터럴 표기법이란?**

코드 상에서 **데이터를 표현하는 방식**을 리터럴이라 하고, 객체지향언어에서는 객체의 리터럴 표기법을 지원한다.

**리터럴 표기법이란, 변수를 선언함과 동시에 그 값을 지정해주는 표기법을 말한다.**

```java
//리터럴 표기법
var no = 3;
var obj = { name: "daeun", age: 20 }; // 객체리터럴 방식으로 만든 객체
```

<br>

## **4. 인스턴스(클래스 데이터)가 리터럴이 될 수 있을까?**

답은 아니다. 
보통의 인스턴스는 동적으로 사용하기 위해 작성되므로 리터럴이 될 수가 없다.  
인스턴스 안에 있는 값들이 언제 바뀔지 모르는 것이기 때문이다. 

하지만 프로그래밍 에서 **객체 리터럴**이란 표현을 들어본적이 있을 것이다. 
데이터가 변하지 않도록 설계를 한 클래스를 불변 클래스라 칭한다. `immutable class` 
해당 클래스는 한번 생성하면 객체 안의 데이터가 변하지 않고 변하는 상황이면 새로운 객체를 만들어준다. 
자바의 String, Color 같은 클래스가 이와 같은 예이다. 
따라서 우리는 "abc" 와 같은 문자열을 자바에서는 '객체 리터럴' 짧게는 '리터럴' 이라고 표현 하는것이다.