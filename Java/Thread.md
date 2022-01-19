## 1. 프로세스와 쓰레드

### **프로세스**

: 실행 중인 프로그램

프로그램을 실행하면 OS로부터 실행에 필요한 메모리를 할당받아 ⇒ **프로세스가 된다.**

구성요소 : 프로그램 수행에 필요한 데이터, 메모리, **쓰레드(프로세스를 이용해 실제로 작업 수행함)**

⇒ 모든 프로세스에는 최소한 하나 이상의 thread가 존재함

### 멀티태스킹

OS에서 멀티태스킹을 지원하기 때문에 여러 개의 프로세스가 동시에 실행될 수 있다. (프로그램)

### 멀티쓰레딩

**하나의 프로세스 내에서 여러 쓰레드가 동시에 작업 수행**

코어는 한번에 단 하나의 작업만 수행할 수 있지만, 처리해야하는 쓰레드의 수는 언제나 코어보다 많기 때문에 코어가 여러 작업을 번갈아가면서 수행한다. ⇒ 동시에 수행되게 보이게 함!

**장점**

채팅 + 파일 다운로드 + 음악듣기 한번에 가능한 이유 !

- CPU 사용률 향상
- 사용자에 대한 응답성 향상
- 작업이 분리되어 코드가 간결해짐

만일 싱글쓰레드로 서버 프로그램을 작성한다면, 사용자의 요청마다 새로운 프로세스를 생성해야함

⇒ 프로세스 생성 > 쓰레드 생성 (많은 시간, 메모리공간 필요함)

## 2. thread 구현과 실행

### 1. thread 클래스 상속

```java
class myThread extends Thread {
	public void run() { 
		// 작업내용 
	}
}
```

### 2. Runnable 인터페이스 구현

```java
class myThread implements Runnable {
	public void run() { 
		// 작업내용 
	}
}
```

```java
class ThreadEx1 {
	public static void main(String args[]) {
		ThreadEx1 t1 = new ThreadEx1();
		
		Runnable r = new ThreadEx2();
		Thread t2 = new Thread(r);    
	}
}
```

## 3. start() run()

### run()

실행 X 단순히 클래스에 선언된 메서드 호출!

### start()

1. call stack 생성 : 새로운 쓰레드가 작업하는데 필요함
2. run 호출 : 생성된 호출스택에 run()이 첫번째로 올라감

각각의 쓰레드에는 자신만의 호출스택이 필요하다. 쓰레드가 종료되면 호출스택은 소멸된다.

### main thread

main 메서드의 작업을 수행함!

## 4. single thread와 multi thread

## 5. Thread 우선순위

```java
void setPriority(int newPriority) // 우선순위를 지정한 값으로 변경한다.
int getPriority() // 우선순위를 반환한다.

public static final int MAX_PRIORITY = 10 // 최대우선순위
public static final int MIN_PRIORITY = 1  // 최소우선순위
public static final int MIN_PRIORITY = 5  // 보통우선순위
```

우선순위는 생성한 thread로부터 상속받는다.

main메서드를 수행하는 thread는 우선순위가 5이므로 main 메서드 내에서 생성하는 thread의 우선순위도 자동으로 5로 생성된다.

start() 실행 전에는 변경 가능

## 6. thread group

서로 관련된 쓰레드를 그룹으로 묶어서 관리한다.

폴더 안에 폴더를 생성하듯 thread 그룹 안에 thread 그룹을 포함시킬 수 있다.

```java
Thread(ThreadGroup group, String name)
Thread(ThreadGroup group, Runnable target)
Thread(ThreadGroup group, Runnable target, String name)
Thread(ThreadGroup group, Runnable target, String name, long stackSize)
```

모든 쓰레드는 반드시 쓰레드 그룹에 포함되어 있어야 한다.

그룹으로 생성자를 사용하지 않은 경우는 ? 자신을 생성한 쓰레드와 같은 그룹에 속하게 됨

JVM이 실행되면 system 스레드 그룹을 만들고, JVM 운영에 필요한 스레드들을 생성해서 system 스레드 그룹에 포함시킨다.

system의 하위 스레드 그룹으로 main을 만들고, 메인 스레드를 main 스레드 그룹에 포함시킨다.

- 관련된 메서드

```java
// 자신이 속한 쓰레드그룹 반환
ThreadGroup getTreadGroup() 

// 처리되지 않은 예외에 의해 종료되면, JVM에 의해 자동으로 메서드 호출됨
void uncaughtException(Thread t, Throwable e) 

```

## 7. deamon thread

일반 쓰레드의 작업을 돕는 보조역할

일반 쓰레드가 종료되면 더이상 쓸모없기 때문에 강제적으로 종료됨

무한루프와 조건문을 이용해서 실행후 대기하고 있다가 특정 조건이 만족하면 작업을 수행하고 다시 대기하도록 작성한다.

```java
boolean isDeamon() // 데몬쓰레드이면 true반환

// 쓰레드를 데몬쓰레드로 변경한다. on=>true로 지정하면 데몬쓰레드가 된다.
void setDeamon(boolean on) 
```

## 8. thread 실행제어

### sleep(long millis)

일정시간동안 쓰레드를 멈추게한다.

```java
static void sleep(long millis)
static void sleep(long millis, int nanos)
```

```java
try {
	Thread.sleep(1, 500000); // 쓰레드를 0.0015초 동안 멈추게 한다
} catch(InterruptedException e) {}

// 새로운 메서드 만들어서 쓰면 편해짐
void delay(long millis) {
	try {
		Thread.sleep(millis);
	} catch(InterruptedException e) {}
}
```

sleep()에 의해 일시정지가 된 쓰레드는 

지정된 시간이 되거나, interrupt()가 호출되면 (InterruptedException 발생) 잠에서 깨어나 실행대기 상태가 된다.

따라서 항상 try-catch문으로 예외처리 해야함!

### interrupt() interrupted()

쓰레드의 작업을 취소한다.

큰 파일을 다운받는다든지, 시간이 너무 오래걸리는 작업이면 중간에 취소시켜야 할 때가 있다.

interrupt()로 쓰레드에게 작업을 멈추라고 요청할 수 있다.

하지만 단순히 멈추라고 **요청**만 하는 것이지 강제로 종료시키지는 못한다.

```java
void interrupt()             // false => true 상태 변경
boolean interrupted()        // 상태 반환
static boolean interrupted() // 상태 반환 & false로 변경
```

### suspend() resume() stop

suspend() : sleep() 처럼 쓰레드를 멈추게 한다.

↔️ resume() : suspend() 에 의해 정지되니 쓰레드를 다시 실행대기 상태로

stop() : 호출되는 즉시 쓰레드 종료

### yield()

자신에게 주어진 실행시간을 다음 차례의 쓰레드에게 양보한다.

⇒ 자신이 점유하고 있던 CPU자원을 내려놓고 실행대기(RUNNABLE) 상태로 들어간다.

현재 실행되고 있는 쓰레드가 그 즉시, 다른 쓰레드에게 실행을 양보하고 실행이 가능한 RUNNABLE한 상태가 된다는 보장은 없어요.

yield() 와 interrupt() 를 잘 사용하면 프로그램의 응답성을 높이고 효율적인 실행이 가능하다.

### join()

쓰레드 자신이 하던 작업을 멈추고 다른 쓰레드가 지정된 시간동안 작업하도록 한다.

## 9. Thread 동기화

### 1. syncronized를 이용한 동기화

스레드가 사용 중인 객체를 다른 스레드가 변경할 수 없도록 하려면 스레드 작업이 끝날 때까지 객체에 잠금을 걸어서 다른 스레드가 사용할 수 없도록 해야 한다.

**멀티 스레드 프로그램에서 단 하나의 스레드만 실행할 수 있는 코드 영역을 임계 영역(critical section)이라고 한다.**

자바는 임계 영역을 지정하기 위해 동기화(synchronized)메소드와 동기화 블록을 제공한다.

쓰레드가 객체 내부의 동기화 메소드 또는 블록에 들어가면 ⇒ 즉시 객체에 잠금을 걸어 다른 스레드가 임계 영역 코드를 실행하지 못하도록 한다. 

- 동기화 메소드를 만드는 방법 :
    
    메소드 선언에 synchronized 키워드를 붙이면 된다. 
    
    synchronized 키워드는 인스턴스와 정적 메소드 어디든 붙일 수 있다.
    

setMemory() 메소드를 동기화 메소드를 만들어서 

User1 스레드가 setMemory()를 실행할 동안 User2 스레드가 setMemory() 메소드를 실행할 수 없도록 했다.

```java
public class Calculator {
    private int memory;
    
    public int getMemory() {
        return memory;
    }
 
    public synchronized void setMemory(int memory) {
        this.memory = memory;
        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + this.memory);
    }
}
```

### 2. wait() 와 notify()

### 3. Lock 과 Conditon을 이용한 동기화

wait() & notify()로는 **기아 현상과 경쟁 상태**를 해결할 수 없다. 

그래서 사용하는 것이 **Lock과 Condition을 이용한 동기화!**

JDK1.5에 와서 'java.util.**concurrent**.locks'라는 패키지가 추가되었다. 여기서 제공하는 lock클래스로 synchronized블럭과 마찬가지로 동기화할 수 있다.

- synchronized 블럭으로 동기화를 하면 자동적으로 lock이 잠기고 풀리기 때문에 편리하다.
    
    심지어 synchronized 블럭 내에서 예외가 발생해도 lock은 자동적으로 풀린다.
    

그러나 때로는 같은 메서드 내에서만 lock을 걸 수 있다는 제약이 불편하기도 하다. 

그럴 때 lock 클래스를 사용한다.

+ ReentrantLock : 재진입이 가능한 lock. 가장 일반적인 배타 lock
+ ReentrantReadWriteLock : 읽기에는 공유적이고, 쓰기에는 배타적인 lock
+ StampedLock : ReentrantReadWriteLock에 낙관적인 lock기능을 추가

### 4. volatile

### 5. fork & join 프레임워크

**JDK1.7**부터 추가되었고, 이 프레임웍은 하나의 작업을 작은 단위로 나눠서 여려 쓰레드가 동시에 처리하는 것을 쉽게 만들어 준다.

먼저 ****수행할 작업에 따라 RecursiveAction과 RecursiveTask 두 클래스 중에서 하나를 상속받아 구현해야 한다.

```java
RecursiveAction          // 반환값이 없는 작업을 구현할 때 사용
RecursiveTask            // 반환값이 있는 작업을 구현할 때 사용

public bastract class RecursiveAction extends ForkJoinTask<Void> {
      protected abstract void compute(); // 반환값 없음
}

public abstract class RecursiveTask<V> extends ForkJoinTask<T> {
    V result; // 반환값
    protected abstract V compute();
}
```

```java
class SumTask extends RecursiveTask<Long> {
      long from, to;

      SumTask(long from, long to) {
          this.from = from;
          this.to = to;
      }

      publicLong compute() {
          // 처리할 작업을 수행하기 위한 문장을 넣는다.
      }
  }
```

쓰레드를 시작할 때run()이 아니라start()를 호출하는 것처럼,

fork&join 프레임웍으로 수행할 작업도 compute()가 아닌 invoke()로 시작한다.

```java
  ForkJoinPool pool = new ForkJoinPool();// 쓰레드 풀을 생성
  SumTask task = new SumTask(from, to);// 수행할 작업을 생성
  Long result = pool.invoke(task);// invoke()를 호출해서 작업을 시작
```

---

***ForkJoinPool*** 은 fork &join 프레임웍에서 제공하는 쓰레드 풀로, 

지정된 수의 쓰레드를 생성해서 미리 만들어 놓고 반복해서 재사용할 수 있게 한다.
