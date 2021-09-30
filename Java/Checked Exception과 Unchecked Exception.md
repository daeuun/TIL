### 1. 예외와 오류

**예외(Exception)**

정상적인 Program의 flow를 어긋나는 것  
개발자가 구현한 로직에서 발생한다. 예외는 발생할 상황을 미리 예측하여 처리할 수 있다.

**오류(Error)**

시스템에 비정상적인 상황이 생겼을 때 발생한다.  
Java에서 주로 JVM(Java Virtual Machine)에서 발생시키는 것이고, Exception과 반대로 Application Code에서 잡아도 처리할 제대로 방법이 없다.

<br>

### 2. RuntimeException

: 프로그램 실행 중에 발생되는 예외처리

RuntimeException을 상속하지 않는 클래스는 `Checked Exception`  
반대로 상속한 클래스는 `Unchecked Exception`


<img src="https://user-images.githubusercontent.com/79685920/135036734-da40eedd-5d29-42be-a452-3449cf284454.png" width="700" height="500"/>


### 3. Unchecked Exception / Checked Exception 비교
||Checked Exception|Unchecked Exception|
|------|---|---|
|처리 여부|반드시 예외 처리 해야함|예외 처리 하지 않아도됨|
|예외발생 확인 시점|컴파일 단계|실행 단계|
|예외발생시 트랜잭션 처리|Rollback 안됨|Rollback 됨|
|대표 Exception|IOException, SQLException, ClassNotFoundException|NullPointerException, IllegalArgumentException|

<br>

### 스프링 트랜잭션 추상화에서 rollback 대상

트랜잭션 작업 중 `RuntimeException이 발생`하면 `rollback`한다. 반면에 예외가 발생하지 않거나 체크 예외가 발생하면 커밋한다.  
체크 예외를 커밋 대상으로 삼는 이유는 체크 예외가 예외적인 상황에서 사용되기 보다는 리턴 값을 대신해서 비즈니스적인 의미를 담은 결과로 돌려주는 용도로 사용되기 때문이다.  
스프링에서는 데이터 엑세스 기술의 예외를 런타임 예외로 전환해서 던지므로 런타임 예외만 롤백대상으로 삼는다.  
하지만 원한다면 체크예외지만 rollbackFor 또는 rollbackForClassName 속성을 이용해서 롤백 대상으로 삼을 수 있다.
