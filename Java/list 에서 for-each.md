Java5 에서부터 for-each 문이 추가됐다. 

### List와 같은 경우는 인덱스를 따로 쓰지 않는이상 for-each문으로 바꾸라고 경고를 한다.

```java
List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

for(Integer i : list){
    System.out.println(i);
}
```

for-each 문은 인덱스를 명시할 필요 없이 알아서 리스트 사이즈만큼 반복되기 때문에 에러 여지도 없어지고 코드도 간결해지는 매우 유용한 문법이지만 *인덱스를 사용할 수 없다.*

<br>

+인덱스를 사용하기 위한 반복문
```java
static <T> void method01(List<T> list){
    for (int i = 0; i < list.size(); i++){
        T t = list.get(i);
        // 인덱스를 사용하는 추가코드...
    }
}
```

리스트를 일반 for문으로 돌렸다. 

for-each에 비해 인덱스를 계산하는 코드가 좀 더 추가되는 것 외에 어떤 문제가 있는 것일까?

<br>

### 컬렉션 구현체들을 인터페이스를 이용해 참조한다.

```java
List<String> list1 = new ArrayList<>();
List<String> list2 = new LinkedList<>();
Set<String> set1 = new HashSet<>();
Set<String> set2 = new LinkedHashSet<>();
Map<String, Object> map1 = new HashMap<>();
Map<String, Object> map2 = new LinkedHashMap<>();
```

왜 인터페이스로 선언하는 걸까 ?

인터페이스를 사용해서 다른 구현체를 사용할 때도 유연하게 교체하기 위함 (자료구조 변경 유연) 인데,  
문제는 인자로 들어오는 List가 ArrayList인지 LinkedList인지 Vector인지 Stack인지 알 수 없다.

여기서 발생하는 문제점은 바로 List에서 사용하는 get()은 자료구조의 내부 구현에 따라 속도가 현저하게 다르다는 것이다.

메서드의 인자는 List<T> 타입으로 받고 있고, List<T>의 구현체는 대표적으로 ArrayList<T>와 LinkedList<T>가 있다. 

<br>
  
### 그리고 이 구현체에 따라서 get() 메서드의 시간 복잡도가 극명하게 달라진다.

그 이유는 무엇일까?

LinkedList의 경우 인자로 전달된 인덱스의 요소를 가져오기 위해서 내부에서 선택한 인덱스까지 다시 순회를 해야 한다.  
get()의 시간 복잡도는 O(n)이다. for문과 결합되면 2중 반복문으로 돌아서 O(n^2)이 된다.

반복문에서 n, 내부에서 다시 탐색하는데 n의 시간이 들어 O(n^2) 시간이 소요된다.

```java
static <T> void method01(List<T> list){
    for(int i = 0; i < list.size(); i++){
        T t = list.get(i);
        // 인덱스를 사용하는 추가코드...
    }
}
```

LinkedList는 get() 메서드 내부에 이미 반복문이 있기 때문에 의도치 않게 2중 반복이 되는 것이다.

구현체는 언제든 바뀔 수 있고, 설사 LinkedList를 쓰지 않더라도 List를 반환하는 외부 라이브러리에서 LinkedList 인스턴스를 반환할 수도 있는 법이다. 다형성을 이용해 구현체에 상관없게끔 코드를 짜고 있었다면 머리속에서도 구현체를 지워야 하는 것이었다.

<br>
  
그렇다면 for-each문은 이러한 문제를 어떻게 해결할까?  
그것은 iterator를 이용하여 요소를 순회하기 때문이다.

<br>
 
자, 그럼 List를 반복할땐 무조건 for-each문을 사용해야하는건 알았다. 

그런데 그럼 인덱스는 어떻게 해야하나?

```java
static <T> void method01(List<T> list){
    int i = 0;
    for(T t : list){
        // 인덱스를 사용하는 추가코드...
        i++;
    }
}
```

List를 반복할 때는 꼭 for-each나 Iterator를 사용하고, 인덱스가 필요한 경우는 명시적으로 인덱스를 사용하지 않을 수 있게 리팩토링할 수는 없는지 고민해 보자.
