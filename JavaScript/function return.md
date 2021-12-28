## return
함수 실행을 종료하고, 주어진 값을 함수 호출 지점으로 반환합니다.

함수는 return을 호출하는 지점에서 즉시 실행을 멈춥니다.

```javascript
function counter() {
  for (var count = 1; ; count++) {  // 무한 반복
    console.log(count + "A"); // 5까지
      if (count === 5) {
        return;
      }
      console.log(count + "B");  // 4까지
    }
  console.log(count + "C");  // 절대 나타나지 않음
}

counter();

// 출력:
// 1A
// 1B
// 2A
// 2B
// 3A
// 3B
// 4A
// 4B
// 5A

```

```javascript
function magic(x) {
  return function calc(x) { return x * 42 };
}

var answer = magic();
answer(1337); // 56154
```
