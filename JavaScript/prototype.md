```javascript
    // 부모
    function 기계() {
        this.q = 'strike';
        this.w = 'snowballl';
    }    
    // 자식
    var nunu = new 기계()
    // 기계 {q: 'strike', w: 'snowballl'}
    // prototype : 유전자
    
    기계.prototype.name = 'kim'
    // 부모인 기계의 prototype에 name을 추가해놓으면
    // => 자식인 nunu에서도 name을 사용할 수 있음!!
    
    nunu.name // 'kim'
    // nunu를 보면 name 속성을 가지고 있지는 않지만 가져다가 쓸 수 있음
    
    // <object에서 자료 뽑을 때 일어나는 일>
    // 1. 직접 자료를 가지고 있으면 출력
    // 2. nunu가 name을 가지고 있지 않으면?? 부모 유전자에 가서 물어봄 => 거기 name이 있으면 출력해줌
    // 3. 부모도 없으면 부모의 부모까지.. 계속 함
    // => prototype chain

    var array = [4, 2, 1]; // 인간에게 편한 방식
    var array = new Array(4,2,1); // 컴퓨터의 방식. 여기서 Array=기계인데 여기서 자식을 하나 꺼내서 써주세요 하는 의미
    
    array.sort() // (3) [1, 2, 4] 정렬해서 출력
    // sort, length, ... 이런 함수들 쓸 수 있는 이유가 뭘까????
    // Array라는 부모유전자에게 기록되어 있기 때문에~!
    // Array.prototype 출력해보면 수많은 함수들이 다 적혀있음....!!!!
    // mdn 사이트에 보면 Array.prototype.sort() << 요렇게 적혀있었던 것도 다 그이유임....
    
    // Q. 모든 array자료에서 쓸 수 있는 함수 추가하기
    Array.prototype.함수 = function(){}
```
