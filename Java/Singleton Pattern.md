## 싱글톤 패턴(Singleton Pattern)이란?

싱글톤은 애플리케이션 상 특정 클래스가 최초 한 번만 메모리를 할당하고 그 메모리에 인스턴스를 만들어 사용하는 디자인 패턴을 의미한다.  
객체 생성 요청이 여러번 발생하더라도 새롭게 메모리를 할당하여 인스턴스를 만드는 것이 아닌 **기존에 생성되어 있던 인스턴스를 참조**하는 것이다.

싱글톤 패턴을 사용한다면 불필요한 메모리 사용을 줄일 수 있다는 장점이 있다.

## JVM에서의 싱글톤

JAVA에서 인스턴스를 생성할 때에는 new 키워드와 함께 생성자를 사용한다.  
그러므로 클래스를 싱글톤으로 만들려면 외부에서 생성자를 호출할 수 없게 private으로 만들어줘야 한다.

``` java
// Java
public final class SingleTonTest {
   @NotNull
   public static final SingleTonTest INSTANCE;

   private SingleTonTest() {
   }

   static {
      SingleTonTest var0 = new SingleTonTest();
      INSTANCE = var0;
   }
}
```

하지만 생성자를 public으로 만듦에 따라 싱글톤이 보장되지 않을 수 있는 가능성이 있다.

## enum class와 싱글톤

enum class는 외부에서 인스턴스화 할 수 있는 생성자 자체가 없다. 
다만, 내부에서 관리되는 상수를 생성하는 생성자는 존재한다.

```java
// Java
enum EnumTest {
    Number("number");

    private final String identifier;

    public final String getIdentifier() {
        return this.identifier;
    }

    private EnumTest(String identifier) {
        this.identifier = identifier;
    }
}
```
