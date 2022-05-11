테스트 조건이 거짓으로 평가될 때까지 지정된 구문을 실행하는 루프를 만든다.  
구문이 실행된 뒤에 테스트 조건이 평가됨으로 구문은 무조건 한 번은 실행된다.
```javascript
let result = '';
let i = 0;

do 
{
  i = i + 1;
  result = result + i;
} 
while (i < 5);

console.log(result);
// expected result: "12345"
```
