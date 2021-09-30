## iterator

자바의 컬렉션 프레임워크에서 컬렉션에 저장되어 있는 요소들을 읽어오는 방법을 표준화한 것

<br>

## Iterator 사용이유

어떤 컬랙션이라도 동일한 방식으로 접근이 가능하여 그 안에 있는 항목들에 접근할 수 있는 방법을 제공한다.(다형성)

<br>

## Iterator 메소드

- hasNext() : 읽어올 요소가 남아있는지 확인하는 메소드이다. 요소가 있으면 true, 없으면 false
- next() : 다음 데이터를 반환한다.
- remove() : next()로 읽어온 요소를 삭제한다.

메소드 호출 순서는 hasNext() ⇒ next() ⇒ remove()이다.

<br>

예시) 리스트에 들어있는  “일”, “월”, “수” 중에서 “수”라는 데이터를 삭제해보자

```java
import java.util.ArrayList;
import java.util.Iterator;

public class Practice {

		public static void main(String[] args) {

			ArrayList list = new ArrayList();
			list.add("일");
			list.add("월");
			list.add("수");

			Iterator iter = list.iterator();
			while (iter.hasNext() == true) {
				String day = (String) iter.next();
				if (day == "수") {
		 			iter.remove();
				}

			System.out.println("Day : " + day);
                      }

			System.out.println("-------------------");

			iter = list.iterator();
			while(iter.hasNext() == true) {
				String day = (String)iter.next();
				System.out.println("Day : " + day);
				}
		}

}
```