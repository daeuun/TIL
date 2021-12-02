## Strict mode
엄격모드는 JavaScript 의 제한된 버전을 선택하여 암묵적인 "느슨한 모드(sloppy mode)" 를 해제하기 위한 방법

+ 기존에는 조용히 무시되던 에러들을 throwing
+ JavaScript 엔진의 최적화 작업을 어렵게 만드는 실수들을 바로잡는다. 가끔씩 엄격 모드의 코드는 비-엄격 모드의 동일한 코드보다 더 빨리 작동하도록 만들어진다.
+ ECMAScript의 차기 버전들에서 정의 될 문법을 금지한다.



## 엄격모드 적용하기

엄격모드는 전체 스크립트 또는 부분 함수에 적용가능.  
단, {} 괄호로 묶여진 블럭문에는 적용되지 않는다.  
컨텍스트와 같은 곳에 적용을 시도하면 동작하지 않는다.  
eval 코드, Function 코드, 이벤트 핸들러 속성, WindowTimers.setTimeout() 과 연관된 함수들에 전달된 문자열이 전체 스크립트이며 여기에서 엄격모드가 예상대로 동작한다. 

### 스크립트 엄격 모드

"use strict";(또는 'use strict';) 을 다른 구문 작성 전에 삽입.

### 함수에 strict mode 적용

"use strict"; (또는 'use strict';) 함수 본문 처음에 작성한다.

```
function strict() { 
    // 함수-레벨 strict mode 문법 
    'use strict'; 
    function nested() { 
        return "And so am I!"; 
    } 
    return "Hi! I'm a strict mode function! " + nested(); 
    } 
function notStrict() { 
    return "I'm not strict."; 
}
```

## 엄격한 모드 변경

엄격한 모드는 구문과 런타임 동작을 모두 변경합니다.
일반적으로 이러한 유형의 변화가 발생합니다: 변환 실수를 오류로 해석하거나(문법 오류 또는 런타임에 오류 발생), 특정 이름의 특정 용도에 대한 특정 변수를 계산하는 방법을 단순화하며,  eval 과 arguments 를 단순화하고,"안전한 "자바 스크립트를 작성하도록 돕고, 미래 ECMAScript의 진화에 대비합니다.

### 실수를 에러로 바꾸는 것

엄격한 모드는 일부 이전에 허용되었던 실수를 오류로 바꿔 놓습니다. 자바 스크립트는 초보 개발자에게 쉬운 것이 되도록 설계되었으며, 때로는 오류를 일으킬만한  동작을 에러없이 시행합니다. 때때로 이것은 즉각적인 문제를 해결하지만, 때때로 이것은 더 심각한 문제를 만들어 냅니다. 엄격한 모드는 이러한 실수를 오류로 처리해서 그것을 발견하고 즉시 고칠 수 있도록 합니다.

1. 엄격모드는 실수로 글로벌 변수를 생성하는 것을 불가능하게 만듭니다. 일반적인 JavaScript에서 변수를 잘못 입력하면 전역 객체에 대한 새 속성이 만들어지고 그대로 "동작" (미래의 오류가 발생할 수 있음: modern 자바 스크립트처럼) 합니다. 전역 변수를 생성하는 할당은 엄격 모드에선 오류를 발생시킵니다.
```
"use strict"; // 전역 변수 mistypedVariable 이 존재한다고 가정 mistypedVaraible = 17; // 변수의 오타때문에 이 라인에서 ReferenceError 를 발생시킴 Copy to Clipboard
```

2. 엄격모드는 예외를 발생시키는 실패를 조용히 넘어가는 대신 작업을 만듭니다. 예를 들어, NaN 은 쓸 수 없는 전역 변수입니다. NaN 에 할당하는 일반적인 코드는 아무 것도 하지 않습니다. 개발자도 아무런 실패 피드백을 받지 않습니다. 엄격 모드에서 NaN 에 할당하는 것은 예외를 발생시킵니다. 일반 코드에서 조용히 넘어가는 모든 실패에 대해 (쓸 수 없는 전역 또는 프로퍼티에 할당, getter-only 프로퍼티에 할당, 확장 불가 객체에 새 프로퍼티 할당) 엄격 모드에서는 예외를 발생시킵니다.
```
"use strict"; // 쓸 수 없는 프로퍼티에 할당 var undefined = 5; // TypeError 발생 var Infinity = 5; // TypeError 발생 // 쓸 수 없는 프로퍼티에 할당 var obj1 = {}; Object.defineProperty(obj1, "x", { value: 42, writable: false }); obj1.x = 9; // TypeError 발생 // getter-only 프로퍼티에 할당 var obj2 = { get x() { return 17; } }; obj2.x = 5; // TypeError 발생 // 확장 불가 객체에 새 프로퍼티 할당 var fixed = {}; Object.preventExtensions(fixed); fixed.newProp = "ohai"; // TypeError 발생
```



















