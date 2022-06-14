### 옵셔널 체이닝
프로퍼티가 없는 중첩 객체를 에러 없이 안전하게 접근할 수 있다.  

### 옵셔널 체이닝이 필요한 이유
```jsx
let user = {
    //address : '서울시'
}; 
// 주소 정보가 없는 사용자 

alert(user.address.street); 
// TypeError: Cannot read property 'street' of undefined
```

### ?.을 사용하면?
?.은 ?.'앞’의 대상이 undefined나 null이면 평가를 멈추고 undefined 반환
```jsx
let user = {
    //address : '서울시'
}; 
// 주소 정보가 없는 사용자 

alert(user.address?.street); 
// undefined
```
user 객체가 존재하지 않더라도 에러가 발생하지 않는다.


### 옵셔널 체이닝을 남용하지 마세요.

?.는 존재하지 않아도 괜찮은 대상에만 사용해야 합니다.  
사용자 주소를 다루는 위 예시에서 논리상 user는 반드시 있어야 하는데 address는 필수값이 아닙니다. 그러니 user.address?.street를 사용하는 것이 바람직합니다.  
실수로 인해 user에 값을 할당하지 않았다면 바로 알아낼 수 있도록 해야 합니다. 그렇지 않으면 에러를 조기에 발견하지 못하고 디버깅이 어려워집니다.

