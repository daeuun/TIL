
## java.lang.ArrayIndexOutOfBoundsException

배열의 크기를 n이라고 했을 때 배열의 인덱스는 1부터 n까지가 아닌 0부터 n-1까지입니다. 

자바는 인덱스가 배열의 크기보다 크거나 음수 인덱스에 대한 요청이 있으면 자바는 위의 예외를 발생시킵니다.

또한 이 예외는 자바 컴파일러는 검사하지않고 항상 런타임(실행도중)에 예외를 발생시킵니다.

다음은 Exception 발생의 예입니다.

```java
int[] arr = {0, 1, 2, 3, 4};
arr[5] = 4; // 예외 발생!
```

다음과 같은 오류가 발생


Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 5 

<br>

## split
특정 문자를 기준으로 문자열을 나누어 *배열(Array)* 에 저장하여 리턴하는 함수

``` javascript
String str = "010-1234-5678"; 

String[] mobNum = str.split("-"); 
String arr1 = mobNum[0]; 
String arr2 = mobNum[1]; 
String arr3 = mobNum[2];
```

### split로 문자열 자를 때 해당하는 특정 문자가 없다면?
배열 0번지에는 무조건 해당 문자열이 들어가겠지만 그 이상으로 접근하면 ArrayIndexOutOfBoundsException 발생
