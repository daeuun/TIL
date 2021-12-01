## Array.splice()

배열의 기존 요소를 삭제 or 교체하거나 새 요소를 추가하여 배열의 내용을 변경

### 문법
```
array.splice(start[, deleteCount[, item1[, item2[, ...]]]])
```

### 매개변수

+ start  
배열의 변경을 시작할 인덱스.  
배열의 길이보다 큰 값이라면 실제 시작 인덱스는 배열의 길이로 설정.  
음수인 경우 배열의 끝에서부터 요소를 센다. (원점 -1, 즉 -n이면 요소 끝의 n번째 요소를 가리키며 array.length - n번째 인덱스와 같음).   
값의 절대값이 배열의 길이 보다 큰 경우 0으로 설정.

+ delete Count (Optional)  
배열에서 제거할 요소의 수.  
deleteCount를 생략하거나 값이 array.length - start보다 크면 start부터의 모든 요소를 제거.  
deleteCount가 0 이하라면 어떤 요소도 제거하지 않음.  
이 때는 최소한 하나의 새로운 요소를 지정해야 함.  

+ item1, item2, ... (Optional)  
배열에 추가할 요소.  
아무 요소도 지정하지 않으면 splice()는 요소를 제거.

### 반환 값

제거한 요소를 담은 배열.  
하나의 요소만 제거한 경우 길이가 1인 배열을 반환.  
아무 값도 제거하지 않았으면 빈 배열을 반환.

<br> 

### 💡하나도 제거하지 않고, 2번 인덱스에 "violet" 추가
```
var color = ['red', 'yello', 'green', 'blue'];
var removed = color.splice(2, 0, 'violet');

// color 결과 ["red", "yello", "violet", "green", "blue"]
```

### 💡2번 인덱스에서 한 개 요소 제거하고 "violet" 추가
```
var color = ['red', 'yello', 'green', 'blue'];
var removed = color.splice(2, 1, 'violet');

// color 결과 ["red", "yello", "violet", "blue"]
```
