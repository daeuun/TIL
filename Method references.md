### Method references 

람다 표현식에서 하는 일이라고는 이미 존재하는 메소드를 호출하는 게 전부인 경우 Method references를 사용할 수 있다.  
이는 말 그대로 (일반적인 람다 표현식의 문법을 따르는 것이 아니라) 그 메소드의 이름을 언급하는 것인데 람다 표현식을 사용한 것보다 간결하고 명확하다.

Person이라는 클래스를 예로 들어보자.

age라는 변수를 가지고 있고, compareTo는 두 개의 Person 객체를 인자로 받아 age를 비교하는 static 메소드이다.

```java
static class Person {
	int age;

	public Person(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public static int compareTo(Person p1, Person p2) {
		if(p1.age > p2.age) return 1;
		else if(p1.age < p2.age) return -1;
		else return 0;
	}

	@Override
	public String toString() {
		return "Person{" + age + "}";
	}

}
```

세 개의 Person 객체를 만든 뒤 배열에 넣고 Arrays의 sort 메소드를 이용하여 정렬해보자.  

sort의 첫 번째 인자는 배열이고, 두 번째 인자는 Comparator 인터페이스이다.

```java
public static void main(String[] args) {
	Person[] persons = new Person[3];
	Person[0] = new Person(10);
	Person[0] = new Person(20);
	Person[0] = new Person(30);

	Arrays.sort(persons, (a, b) -> Person.compareTo(a, b));

	Arrays.stream(persons).forEach(System.out::println);
}

```


Comparator 인터페이스는 functional interface로, compare이라는 메소드를 갖는다.

compare는 같은 타입의 객체 두 개를 받아 비교 결과를 int로 리턴하는 메소드이다.

```java
Arrays.sort(persons, (a, b) -> Person.compareTo(a, b));

Arrays.stream(persons).forEach(System.out::println);
```
Person 클래스에서 이미 compareTo라는 메소드를 구현했으므로 compare에서는 그냥 Person 클래스의 compareTo 메소드를 호출하여 Person 객체 간의 크기를 비교하면 된다.

다시 말해,

functional interface이므로 메소드가 하나이기 때문에 익명 클래스가 아닌 람다 표현식을 이용할 수 있고,

메소드에서 하는 일이라고는 이미 존재하는 메소드를 호출하는 것이기 때문에 람다 표현식이 아닌 Method references를 이용할 수 있다.

