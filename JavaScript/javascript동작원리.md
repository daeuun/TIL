```js
console.log(1+1) // 1등
    setTimeout(function() { console.log(2+2)}, 1000) // 1초 기다렸다가 3등
    console.log(3+3) // 2등

    // 코드가 위에서 아래로 시작되는게 아니라 처리를 빠르게 한거부터 실행되넹

    // 브라우저 동작원리를 알면 이해됨!
    // 브라우저 = 자스 실행해주는 애들
    
    // * Stack *
    // 안에 코드를 한줄한줄 쌓아놓은걸 실행함
    // ( Heap에 선언해놓은 변수를 가져다가 실행함 )
    // 특징 : 하나임 => 자바스크립트는 보통 single threaded

    // 요로코롬 순서대로 Stack 안에 순서대로 쌓아놓고 실행함
    console.log(1+1)
    console.log(3+3)

    // 아니 근데 setTimeout은 순서대로 실행 안하잖아

    console.log(1+1)
    console.log(3+3) // 2등
    
    // 처리가 오래걸리는 코드들은 대기실에 치워둔답니다
    // Ajax, 이벤트리스너(버튼 누르면 ~해주세요 이런거), setTimeout
    setTimeout(function() { console.log(2+2)}, 1000) 

    // 시간이 다 되면 다시 Stack으로 보내줘야하잖아
    // * Queue *
    // 대기끝난코드1 대기끝난코드2 대기끝난코드3
    // 실행 순서에 따라 줄을 세워놓고 스택으로 차례대로 보내줌
    // 조건!! Stack이 비어있을 때만 올려보냄 

    // 그럼 차례대로 순서 생각해보면

    // 1. Stack
    console.log(1+1) // 1등 실행
    setTimeout(function() { console.log(2+2)}, 1000) // 바로 실행할 수 없으니까 대기실로 보냄
    console.log(3+3) // 2등 실행
    
    setTimeout(function() { console.log(2+2)}, 1000) // 대기실에 있던 코드 1초 후에 실행되어야하니까
    console.log(2+2) // Queue로 가고 => Stack으로 이동


    // 10초 걸리는 어려운 연산을 시키면? 안돼안돼..
    // Stack에서 10초동안 실행하고 있음..
    // 대기실에 버튼을 누르면 모달을 띄워줘 << 가 있는데 눌러도 안뜸
    // 왜냐면 이런 이벤트 리스너는 대기실을 거쳐서 => Queue를 거쳤다가 => Stack으로 올라가는데
    // Stack이 비어있을 때만 가능해서 응답대기 메세지됨...


    // 자바스크립트는 동기적 vs 비동기적?
    // 자바스크립트는 동기적이다. => 한번에 한 줄 순서대로 처리됨
    // 근데 setTimeout, 이벤트리스너, ajax 같은거 쓰면 비동기적인 처리도 가능
```
