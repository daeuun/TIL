## javascript
javascript는 Dynamic typing 을 지원하는 언어

``` javascript
5 - '3'
// 자바스크립트에서는 자유도가 높아서 int형 - 문자형으로 연산해도 알아서 계산해줌
// but, 규모가 큰 프로젝트에서 이렇게 자유도가 높은건 좋은게 아님
// TypeScript에서는 에러가 날 수 있다고 타입을 엄격하게 검사해줌
```
+ 자바스크립트는 에러메세지가 추상적임
+ 타입스크립트는 구체적으로 알려줌


## typescript 타입지정

``` typescript
// 브라우저는 ts파일을 못 읽어서 무조건 js파일로 변환해서 써야함 

let 이름 :string = '이다은';
//이름 = 123; // 에러 Type 'number' is not assignable to type 'string'.ts(2322)

let 이름2 :String[] = ['이다은', '이오리'];
// array[] 가 들어올건데 배열 안에 구성하는 타입도 앞에 지정해줘야함 => 이름2 변수에는 String타입이 들어간 배열만 들어올 수 있어~! 

let 이름3 :{ name? : String } = { name : '이다은' };
// Object 
// name? = name속성은 옵션 = 들어올 수도 있고 아닐 수도 있다

let 이름4 :string | number = '이다은';
이름4 = 123; // 오류가 나지 않음!
// 다양한 종류의 타입 지정 Union Type (|기호 사용)

type Name = String | number;
let 이름5 :Name = '이다은';
// 타입을 변수에 담아서 쓸 수 있음 Type Alias
// 오.. 방금 오류났는데 오타 생긴 것도 다 잡아줌 Unknown keyword or identifier. Did you mean 'type'?ts(1435) Cannot find name 'Type'.ts(2304)
// 타입명은 주로 대문자로 많이 사용함

function 함수(x :number) :number {
    return x * 2;
}
// 함수 파라미터에서도 타입 지정 가능
// return에 대한 타입 지정은 ()중괄호 다음에 작성

type Member = [number, boolean];
// Array에 쓰는 tuple 타입
// => 무조건 이 Array의 첫번째는 number, 두번째는 boolean
let ori:Member = [123, true];

type Member2 = {
    name : string
}
let ori2 :Member2 = { name : 'daeun'};

// object에 지정해야할 속성들이 너무 많으면 key에 담아서 한번에 지정하기
// => 글자로 된 모든 object의 속성의 타입은 string
type Member3 = {
    [key:string] : string,
}
let ori3 :Member3 = { name : 'daeun', age : '29'};

class User {
    name :string;
    constructor(name :string) {
        this.name = name;
    }   
}
// class 타입지정

```

## tsconfig.json
ts -> js로 컴파일 하는 옵션 설정하는 곳
```json
{
    "compilerOptions": {
        "target": "es5",
        "module": "commonjs",
    }
}
```



## 2. TypeScript의 장점

### 2.1 정적 타입
TypeScript를 사용하는 가장 큰 이유 중 하나는 정적 타입을 지원한다는 것이다. 아래 함수를 살펴보자.
```javascript
function sum(a, b) {
  return a + b;
}
```

위 함수를 정의한 개발자의 의도는 아마도 2개의 숫자 타입 인수를 전달받아 그 합계를 반환하려는 것으로 추측된다. 하지만 코드상으로는 어떤 타입의 인수를 전달하여야 하는지, 어떤 타입의 반환값을 리턴해야 하는지 명확하지 않다. 따라서 위 함수는 아래와 같이 호출될 수 있다.

```javascript
function sum(a, b) {
  return a + b;
}

sum('x', 'y'); // 'xy'
```

위 코드는 자바스크립트 문법상 어떠한 문제도 없으므로 자바스크립트 엔진은 아무런 이의 제기없이 위 코드를 실행할 것이다. 이러한 상황이 발생한 이유는 변수나 반환값의 타입을 사전에 지정하지 않는 자바스크립트의 동적 타이핑(Dynamic Typing)에 의한 것이다.

위 함수를 TypeScript의 정적 타입을 사용하여 다시 작성 해보자.
```javascript
function sum(a: number, b: number) {
  return a + b;
}

sum('x', 'y');
// error TS2345: Argument of type '"x"' is not assignable to parameter of type 'number'.
```

TypeScript는 정적 타입을 지원하므로 컴파일 단계에서 오류를 포착할 수 있는 장점이 있다. 명시적인 정적 타입 지정은 개발자의 의도를 명확하게 코드로 기술할 수 있다. 이는 코드의 가독성을 높이고 예측할 수 있게 하며 디버깅을 쉽게 한다.

### 2.2 도구의 지원
TypeScript를 사용하는 이유는 여러가지 있지만 가장 큰 장점은 IDE(통합개발환경)를 포함한 다양한 도구의 지원을 받을 수 있다는 것이다. IDE와 같은 도구에 타입 정보를 제공함으로써 높은 수준의 인텔리센스(IntelliSense), 코드 어시스트, 타입 체크, 리팩토링 등을 지원받을 수 있으며 이러한 도구의 지원은 대규모 프로젝트를 위한 필수 요소이기도 하다.

### 2.3 강력한 객체지향 프로그래밍 지원
인터페이스, 제네릭 등과 같은 강력한 객체지향 프로그래밍 지원은 크고 복잡한 프로젝트의 코드 기반을 쉽게 구성할 수 있도록 도우며, Java, C# 등의 클래스 기반 객체지향 언어에 익숙한 개발자가 자바스크립트 프로젝트를 수행하는 데 진입 장벽을 낮추는 효과도 있다.

### 2.4 ES6 / ES Next 지원
브라우저만 있으면 컴파일러 등의 개발환경 구축없이 바로 사용할 수 있는 ES5와 비교할 때, 개발환경 구축 관점에서 다소 복잡해진 측면이 있지만 현재 ES6를 완전히 지원하지 않고 있는 브라우저를 고려하여 Babel 등의 트랜스파일러를 사용해야 하는 현 상황에서 TypeScript 개발환경 구축에 드는 수고는 그다지 아깝지 않을 것이다. 또한, TypeScript는 아직 ECMAScript 표준에 포함되지는 않았지만 표준화가 유력한 스펙을 선제적으로 도입하므로 새로운 스펙의 유용한 기능을 안전하게 도입하기에 유리하다.

### 2.5 Angular
마지막으로 Angular는 TypeScript 뿐만 아니라 자바스크립트(ES5, ES6), Dart로도 작성할 수 있지만 Angular 문서, 커뮤니티 활동에서 가장 많이 사용되고 있는 것이 TypeScript이다. Angular 관련 문서의 예제 등도 TypeScript로 작성된 것이 대부분이어서 관련 정보를 얻을 때 이점이 있으며 이러한 현상은 앞으로도 지속될 것으로 예상된다.
