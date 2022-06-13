### Map 의 종류 

HashMap

TreeMap

LinkedHashMap

1. HashMap

key:value 한쌍으로 데이터를 저장한다. 중복된 키가 존재하지 않는다. 마치 키를 리스트나 배열에 존재하는 idx(인덱스)처럼 가져와 value를 뽑기 때문에 시간복잡도가 O(1)이다. 

2. TreeMap

TreeMap은 데이터가 들어올 때마다 Key 값에 따라 알아서 자동으로 정렬이 된다. 

3. LinkedHashMap

LinkedHashMap은 입력순서를 보장한다. MultivalueMap 키의 중복이 허용된다. 

```java
MultivalueMap<String, Integer> multivalueMap = new LinkedMultiValueMap<>(); 
multivalueMap.add("A", 100); 
multivalueMap.add("A", 200); 
multivalueMap.add("A", 300); 

List<Integer> a = multivalueMap.get("A"); 
for(int data:a) { 	
System.out.println(data); 
} 
// 출력결과값: 
// 100 
// 200 
// 300 
```

MultiValueMap은 키를 가져올때 리스트 형태로 반환하여 같은 키 "A"를 받은 밸류들을 다 저장해둔다.  
Map은 키를 가져오면 밸류의 타입으로 반환하지만, MultiValueMap은 다른 형태다.

### ⁉️HashMap에 중복된 key에 다른 value 넣는 방법

: value를 List형태로 넣어줘야 한다. (MultiValueMap의 방식대로)

```java
Map<String,List<Integer>> map = new HashMap<>(); 
List<Integer> list = new ArrayList<>(); 
list.add(100); 
list.add(200); 
list.add(300); 
map.put("A", list); 

List<Integer> a = map.get("A"); 
for(int data:a) { 	
System.out.println(data); 
} 
```

### (결론) MultiValueMap을 쓰는 이유 
MultiValueMap를 쓰면 애초에 List형태의 값들을 Value로 바인딩 해주기 때문에 직관적이고 깔끔함.

