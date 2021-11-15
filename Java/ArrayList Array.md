우선 ArrayList 는 클래스이고, List 는 인터페이스라는 점에서 차이가 있다.

 

클래스인 ArrayList 를 사용해서 생성 시점에 List 외에도  RandomAccess,Serializable 인터페이스 등을 구현할 수 있는 것이다.

 

클래스 와 인터페이스에 대해서는 이전 게시글을 참고!

2019/11/20 - [자바/기본 개념] - 추상클래스와 인터페이스 / 상속과 다형성

 

만약, 이렇게 선언을 했다면  

       List list = new ArrayList();

       ArrayList list = new ArrayList();

 

List는 인터페이스이므로 도형에 비유할 수 있고,

ArrayList 는 클래스이므로 정사각형이라고 비유할 수 있다.

 

 

 

그렇다면 ArrayList 는 그냥 배열 Array와 비교했을 때 어떤 차이가 있을까?

 

1) ArrayList 는 동적으로 크기를 변경할 수 있다.

import java.util.ArrayList;

 

ArrayList<Integer> list = new ArrayList<Integer>();

list.add(1);

list.add(2);

System.out.println(list.size()); //2

list.remove(2);

System.out.println(list.size()); //1

 

2) '특정' 위치에 element를 추가, 삭제가 가능하다. (null element 도 저장 가능)

import java.util.ArrayList;

 

ArrayList<String> list = new ArrayList<String>();

list.add("one");

list.add("two");

list.add(null);

System.out.println(list);  //[one, two, null]

 

list.add(1, "1.5");  //[one, 1.5, two, null]

list.remove(1);    //[one, two, null]

 

3) Generic 을 사용하지 않고 선언한다면 다양한 타입의 객체를 저장할 수 있다.

import java.util.ArrayList;

 

ArrayList list = new ArrayList();

list.add("one");

list.add(2);

list.add(new Float(3.0));

System.out.println(list);        //[One, 2, 3.0]

 

 

4) ListIterator 를 사용해 양방향 순회가 가능하다.

ListIterator에 대해 모르겠다면 이전 게시글을 참고!

2019/11/20 - [자바/기본 개념] - Iterator과 ListIterator

 

import java.util.ArrayList;

import java.util.ListIterator;     //import java.util.*

 

ArrayList<String> list = new ArrayList<String>();

list.add("one");

list.add("two");

list.add("three");

ListIterator iterator = list.Listiterator();

 

//순방향

while (iterator.hasNext()) {.                  

     System.out.println(iterator.next());

}

//역방향

while (iterator.hasPrevious()) {

     System.out.println(iterator.previous());

} 

 

 

 

 

ArrayList <-> Array 간 변환 ? 

 

1) Array -> ArrayList

  1.1) 하나씩 옮기기

  * int와 같은 primitive type의 경우 사용

   int[] arr = {1,2,3};

   List<Integer> list = new ArrayList<>();

   for (int i : arr) {

         list.add(i);

   } 

   System.out.println(list);     //[1,2,3]

 

  1.2) Arrays.asList() 메소드 사용하기

  * 가장 많이 사용

   String [] arr = new String[] {"one", two", "three"};

   List<String> list = new ArrayList<>(Arrays.asList(arr));

   System.out.println(list);    //[one, two, three]

   

  1.3) Collections.addAll() 메소드 사용하기

  * src 와 dst 의 복사

   String[] arr = new String[] {"one", "two", "three"};

   List<String> list = new ArrayList<>();

   Collections.addAll(list, arr);

   System.out.println(list);      //[one, two, three]

 

  1.4) List 인터페이스의 addAll() 메소드 사용하기

   String[] arr = new String[] {"one", "two", "three"};

   List<String> list = new ArrayList<>();

   list.addAll(Arrays.asList(array));

   System.out.println(list);      //[one, two, three]

  

 

2) ArrayList -> Array

  2.1) array를 크기에 맞게 생성한 후 List 인터페이스의 toArray() 메소드 사용하기

 

   List<String> list = new ArrayList<String>();

   list.add("one");

   list.add("two");

   list.add("three");

   String[] arr = new String[list.size()];

   list.toArray(arr);

 

   for(String s : arr) {

         System.our.print(s);     //one two three

   }

