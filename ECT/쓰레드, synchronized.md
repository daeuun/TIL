### 쓰레드, synchronized 

클래스를 하나 수행하거나 WAS를 기동하면, 서버에 자바 프로세스 하나 생성한다.(서버마다 다름. 여러개 생성할 수 있음)

하나의 프로세스에서는 여러 개의 쓰레드 생성되고 수행된다.

웹 기반 시스템에서 스레드 관련 가장 많이 사용하는 것이 synchronized

```java
public synchronized void sampleMethod(){
}
//메서드를 동기화

public void sampleBlock(){     
    synchronized(obj){ 
    //특정부분만을 동기화    
    } 
}
```

