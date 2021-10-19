## 비동기식 처리 모델

처리가 종료하면 호출될 함수(콜백함수)를 미리 parameter로 전달하고 처리가 종료하면 콜백함수를 호출하는 것이다.  
비동기 처리 모델은 요청을 병렬로 처리하여 다른 요청이 중단되는 것을 막아주는 장점이 있지만,   
여러 개의 콜백함수가 nesting되어 복잡도가 높아지는 **Callback Hell**문제가 있다.  
Callback Hell : 코드의 가독성을 나쁘게 하여 실수를 유발시킬 확률이 높아지며 **에러 처리가 곤란**

이러한 문제를 극복하기 위해 Promise가 제안되었다.

<br>

💡 **Promise**

비동기 처리가 성공하였는지 실패하였는지 등의 상태 정보와 처리 종료 후 실행될 콜백함수(then, catch)담고 있는 객체이다.

💡 **jQuery Deferred**

각각의 비동기식 처리에 Promise 객체를 연계하여 그 상태를 전파하는 것으로 promise를 구현한 jQuery 객체이다.

제이쿼리는 promise를 사용할 수 있게 **Deferred**라는 객체를 제공하고, Deferred를 사용하면 일반 코드도 promise처럼 사용할 수 있다.

<br>

## **Promise**

문자 그대로 약속을 표현하는 자바스크립트 객체다.    
약속은 지켜질 수 있고 물론 지켜지지 않을 수도 있다.  
예를 들어, 선생님이 학생에게 숙제를 주었고 학생은 선생님께 숙제를 해오겠다고(미래의 언젠가) 약속(promise)했다. 

이때 선생님은 다음과 같은 경우의 수를 생각할 수 있다.

1. 학생이 약속을 지키고, 숙제를 해오는데 100점이다. 그러면 사탕을 10개 준다.
2. 학생이 약속을 지키고, 숙제를 해오는데 점수가 100점이 아니다. 그러면 사탕을 5개 준다.
3. 학생이 약속을 지키지 않고, 숙제를 해오지 않았다. 그러면 벌점을 준다.

```jsx
var promiseWithStudent = Student.doHomework(homework);

promiseWithStudent.then(
	  //약속을 지킨 경우  
		//then 메소드의 첫번째 인자로 전달되는 콜백함수는 약속이 지켜지면 실행된다.
    function(data) {
        if (Teacher.makeScore(data) === 100) { 
            Teacher.giveCandy(100, Student);
        } else {
            Teacher.giveCandy(50, Student);

        }
    },
		//약속을 지키지 않은 경우
    //then 메소드의 두번째 인자로 전달되는 콜백함수는 약속을 어기면(취소/거절) 실행된다.
    function(error) {
        Teacher.giveMinusPoint(1000000, Student);
    }
);
```

학생이 숙제를 해온다고 약속하면 위 코드에서는 promise 객체를 반환한다고 생각하면 된다.   
그리고 약속이 지켜질 때와 지켜지지 않을 때를 then 메서드를 이용해 정의하는데   
지켜지면 첫 번째 콜백 함수가 호출되고 지켜지지 않으면 두 번째 콜백 함수가 호출된다.   
promise 객체는 미래에 지켜지거나 지켜지지 않을 일을 객체로 표현하고, 이러한 일들에 대한 처리를 then 메서드를 이용해 처리한다.

<br><br>

```jsx
// jQuery 1.5 이전
$.ajax({
 	  url : '/getOTP'
	, success : successFunction
	, error : errorFunction
})
```

```jsx
// jQuery 1.5 이후
var p = $.ajax({
	url : '/getOPT'
}
p.done(successFunction);
p.fail(errorFunction);
```

<br><br>

## **Deferred**

약속이 지켜지거나 거절될 때의 일을 정의하였으면 누군가는 약속을 지키거나 거절해야 한다. 

이러한 일을 하는 것이 바로 deferred 객체다. 

약속을 만든 사람이 약속을 거절하고 취소하듯이 deferred는 약속을 만들고 이 약속의 상태를 변경한다.

1. `$.Defferred()`를 선언하여 동기화 시킬 상태를 저장
2. 선언된 상태에 원하는 결과가 완료되었다면 `deferred.resolve()`, 
    
    결과가 실패했다면 `deferred.reject()`를 선언하여 선택적으로 결과 제어 
    
3. 제어한 값은 `deferred.promise()`를 이용하여 리턴

```jsx
function test1(){
	var dfd = $.Deferred();       //Deffered 선언
	setTimeout(function(){ 
		console.log("1 end");
		dfd.resolve("result 1");  //정상적으로 완료되면 resolve()를 선언
	}, 3000);
	return dfd.promise();         //promise()를 선언하여 리턴
}

function test2(){ 
	var dfd = $.Deferred();
	try{
		setTimeout(function(){ 
			console.log("2 end");
			dfd.resolve("result 2");
		}, 1000);
	}catch(e){               // 에러가 날 상황을 가정하여 try catch
			dfd.reject('fail2'); // 에러가 나면 reject()로 최종 결과가 실행되지 않는다.
	}
	return dfd.promise();
}
```
<br>

### **promise의 기본 구조**

promise 객체가 완료되었을 때 **done** 메소드가 호출 

실패했을 때는 **fail** 

완료되었건 실패했건 행동이 끝났으면 **always**가 호출 

```jsx
var longAndComplicatedFunction = function() {
  try {
    // 완료되려면 오래걸리는 복잡한 코드
    console.log('성공');
  } catch (err) {
    console.log('실패');
  }
};
longAndComplicatedFunction();
console.log('다음 행동');
```

이렇게 복잡한 코드가 있으면 이 코드를 실행하는 동안 아무 것도 할 수 없기 때문에 비동기 프로그래밍이 필요하다. 

<br>

### **$.Deferred**

콜백 형식은 점점 더 코드가 복잡해지는 문제를 발생시키기 때문에, 

콜백이 여러 번 중첩될 것 같으면 promise 형식을 사용해야 한다. 

```jsx
var longAndComplicatedFunction = function() {
  var deferred = $.Deferred();   // $.Deferred()로 deferred 객체를 만들기
  try {
    // 완료되려면 오래걸리는 매우 복잡한 비동기 코드
    deferred.resolve('성공');    // 성공: resolve메소드 => done()연결
  } catch (err) {
    deferred.reject(err);       // 실패: reject메소드 => fail()연결
  }
  return deferred.promise();    // 함수 안에서 deferred.promise()를 return하기
};

longAndComplicatedFunction().done(function(message) {
  console.log(message);
}).fail(function(error) {
  console.log(error);
}).always(function() {
  console.log('완료!');
});

console.log('다음 행동');
```

<br>

### **then**

done이나 fail로 구분하지 않고 한 번에 처리하는 메소드

- 첫 번째 인자는 성공 시 콜백이고, 두 번째 인자는 실패 시 콜백함
- then도 연달아 쓸 수 있다.

```jsx
longAndComplicatedFunction().then(function(message) {
  console.log(message);
}, function(error) {
  console.log(error);
}).then(function() {
  console.log('완료!');
});
```