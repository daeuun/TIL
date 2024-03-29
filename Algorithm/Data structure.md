### 배열 (Array)

가장 기본적인 데이터 구조다. 

생성시 설정된 셀의 수가 고정되고, 각 셀에는 인덱스 번호가 부여된다.

배열을 활용 시 부여된 **인덱스**를 통해 해당 셀 안에 있는 데이터에 접근 할 수 있다.

✔️ 시간복잡도

| 자료구조 | 가져오기(값) | 추가하기 | 삭제하기 |
| --- | --- | --- | --- |
| Array | O(n) | O(n) | O(n) |

**장점**

- 바로 만들어서 활용하기가 쉽다
- 더 복잡한 자료 구조의 기초가 될 수 있다
- 원하는 데이터를 효율적으로 탐색/가져올 수 있다
- 정렬에 용이하다

**단점**

- 데이터를 저장 할 수 있는 메모리 크기가 고정되어 있다
- 데이터 추가 / 삭제 방법이 비효율적이다
- 구조 재구성 시 정렬하는 방식이 비효율적이다

**사용**

- 엑셀의 스프레드시트 처럼 직사각형 테이블, 수학적 벡터 (vector) 및 행렬 (matrix)를 구현하는 데 사용된다
- 다른 데이터 구조에서 사용된다

<br>

### 스택 (Stack)

스택은 순서가 보존되는 선형 데이터 구조 유형이다. 

가장 마지막 요소 (가장 최근 요소)부터 처리하는 LIFO (Last In First Out)

✔️ 시간복잡도

| 자료구조 | 가져오기(값) | 추가하기 | 삭제하기 |
| --- | --- | --- | --- |
| Stack | O(n) | O(1) | O(1) |

**장점**

- 동적인 메모리 크기
- 데이터를 받는 순서대로 정렬된다
- 빠른 런타임 (runtime)

**단점**

- 가장 최신 요소만 가져온다
- 한번에 하나의 데이터만 처리 가능하다

**사용**

- 가장 마지막으로 입력된 것을 순차적으로 바로 처리하고 싶을 때
- 브라우저의 뒤로가기
- 실행 취소
- 재귀

<br>

### 큐 (Queue)

큐는 스택과 비슷하지만 가장 먼저 입력된 요소를 처리하는 FIFO (First In First Out)

✔️ 시간복잡도

| 자료구조 | 가져오기(값) | 추가하기 | 삭제하기 |
| --- | --- | --- | --- |
| Queue | O(n) | O(1) | O(1) |

**장점**

- 동적인 메모리 크기
- 데이터를 받는 순서대로 정렬된다
- 빠른 런타임 (runtime)

**단점**

- 가장 오래된 요소만 가져온다
- 한번에 하나의 데이터만 처리 가능하다

**사용**

- 반복적이고 자주 받는 데이터를 비동기적으로 처리 할 때 효율적
- 음성 데이터 처럼 순서에 민감한 데이터를 처리 할 때
- 프린트 대기열처럼 가장 먼저 입력 받은 데이터를 먼저 처리해야 할 때
- 캐시(Cache) 구현

<br>


### 연결 리스트 (Linked list)

앞에서 말한 세 가지 구조와 달리 메모리에 있는 데이터의 물리적 배치를 사용하지 않는 데이터 구조다. 

Index나 위치보다 **참조 시스템**을 사용한다. 

각 요소는 **노드**라는 것에 저장되는데, **다음 노드** **연결**에 대한 포인터 또는 주소가 포함된 또 다른 노드에 저장된다. 

모든 노드가 연결된 때까지 반복이 돼서 노드의 연결로 이루어진 자료 구조다. 

그리고 이 구조는 데이터 추가 및 삭제 시 재구성이 필요 없어서 효율적이다.

연결 리스트에는 단일 연결 리스트(Singly-Linked List), 이중 연결 리스트(Doubly-Linked List)등의 종류가 있다.

✔️ 시간복잡도

| 자료구조 | 가져오기(값) | 추가하기 | 삭제하기 |
| --- | --- | --- | --- |
| Linked list | O(n) | O(1) | O(1) |

![https://media.vlpt.us/images/jha0402/post/3240289e-a524-4e26-bce6-3cc24ef37f27/linked_list.png](https://media.vlpt.us/images/jha0402/post/3240289e-a524-4e26-bce6-3cc24ef37f27/linked_list.png)

**장점**

- 새로운 요소들의 추가 및 삭제가 용이하고 효율적이다
- 배열처럼 메모리에 연속적으로 위치하지 않는다
- 배열처럼 구조의 재구성이 필요없다
- 동적인 메모리 크기
- 메모리를 더 효율적으로 쓸 수 있기 때문에 대용량 데이터 처리 적합

**단점**

- 배열보다 메모리를 더 사용한다
- 처음부터 끝까지 순회하기 때문에 원하는 값을 비효율적으로 검색/가져온다
- 노드를 반대 방향으로 검색할 때 비효율적이다 (이중 연결 리스트의 경우)

**사용**

- 메모리 크기가 정해져 있지 않을 때
- 데이터를 연속적으로 빠르게 삽입/제거가 필요 할 때
- 이미지 뷰어, 갤러리
- 음악 플레이어

<br>


### 해시 테이블 (Hash tables / Hash map)

**대량의 정보**를 **저장**하고 특정 요소를 **효율적**으로 **검색**할 수 있는 복잡한 데이터 구조다. 

테이블 내에 더 작은 서브그룹인 **버킷**(bucket)에 **키/값**(key/value)을 저장한다. 

해시 테이블은 키를 저장할 때에 메모리 공간을 덜 사용할 수 있도록, 키를 "해시 함수"(Hash function)라는 함수를 통해 **해시**(hash)라는 특정 숫자값으로 **변환**한다. 

해시 테이블은 필요할 때에만 **메모리 크기**를 **늘리고**, 가능한 **작은 크기**를 **유지**한다.

키(key)는 **검색 시** 사용되는 문자열이고 값(value)은 해당 키와 **쌍**을 이룬 **데이터**다. 

검색된 각 키는 미리 정의된 해시함수(hash function)를 통해 해시(hash)값을 받고 버킷(bucket)을 가리킨다.

즉, 해시 숫자는 **버킷의** **index**라는 뜻이다. 그리고 버킷에서 검색할 때 입력된 키를 찾고 **해당 키**와 **관련된** **값**을 반환한다.

✔️ 시간복잡도

| 자료구조 | 가져오기(값) | 추가하기 | 삭제하기 |
| --- | --- | --- | --- |
| Hash tables | O(n) | O(1) | O(1) |

**장점**

- 새로운 요소들의 추가/삭제가 용이하고 효율적이다
- 원하는 값의 검색/가져오기가 빠르고 효율적이다
- 동적인 메모리 크기 (그러나 직접 크기를 늘리거나 줄여야 한다)

**단점**

- 충돌이 일어날 수 있다 (입력된 키의 해시값이 이미 데이터가 저장된 버킷을 가리킬 수 있다)
- 충돌이 자주 일어날 수 있으며 해시함수의 정비가 필요한 경우가 많다.

**사용**

- 데이터베이스 : 주소 찾기, 이름 찾기, 번호 찾기
- 사용자 로그인 인증

<br>


### 그래프 (Graph)

그래프는 단순히 **nodes/vertices(노드)** 사이에 **edge(엣지)**가 있는 collection이다. 

그래프는 directed(방향) 또는 undirected(무방향)이 될 수 있다. 

Directed graph는 한쪽 방향 밖에 없어서 일방통행과 같고, undirected graph는 방향이 지정되지 않아서 양방향 도로와 같다.

그래프로 구조를 어떻게 설계 그리고 무엇을(검색, 추가, 삭제, 등) 하고 싶으냐에 따라 시간 복잡도가 달라진다.

그래프가 리스트 형태로 설계 되어 있는 경우: N = node, E = edge

✔️ 시간복잡도

| 자료구조 | 가져오기 | 노드/엣지 추가하기 | 노드 삭제하기 | 엣지 삭제하기 |
| --- | --- | --- | --- | --- |
| Graph | O(|N| + |E|) | O(1) | O(|N| + |E|) | O(|E|) |


**장점**

- 새로운 요소들의 추가/삭제가 용이하고 효율적이다
- 구조의 응용이 가능하다

**단점**

- 충돌이 일어날 수 있다 (입력된 키의 해시값이 이미 데이터가 저장된 버킷을 가리킬 수 있다)

**사용**

- 데이터베이스 : 주소 찾기, 이름 찾기, 번호 찾기
- 사용자 로그인 인증

<br>


### 트리 (Tree)

트리는 노드로 구성된 계층적 자료구조다. 

최상위 노드(루트)를 만들고, 루트 노드의 child를 추가하고, 그 child에 또 child를 추가하는 방식으로 트리 구조를 구현할 수 있다.

![https://media.vlpt.us/images/jha0402/post/74df176f-6814-4e7a-b599-275556f2338e/tree_bigO.png](https://media.vlpt.us/images/jha0402/post/74df176f-6814-4e7a-b599-275556f2338e/tree_bigO.png)

트리의 종류는 다양하다.

트리 구조와 관련하여 반드시 알아야 할 개념들:

- A, B, C, D 등 트리의 구성요소를 **노드(node)** 라고 한다.
- 위 그림의 A처럼, 트리 구조에서 최상위에 존재하는 노드를 **root**라고 한다.
- 루트를 기준으로, 다른 노드로의 접근하기 위한 거리를 **depth** 라고 한다.
- 같은 부모를 가지면서 같은 depth에 존재하는 노드들은 **sibling** 관계에 있다.
- 그림에서 A는 B와 C의 **부모(parent)** 이고, B와 C는 A의 **자식(child)이**다.
- 노드와 노드를 잇는 선을 **edge** 라고 한다.
- 자식이 없는 노드는 **leaf** 라고 한다.
