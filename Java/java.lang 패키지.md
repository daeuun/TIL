### 1. Object

자바에서 모든 클래스는 사실 Object를 암시적으로 상속받고 있다.

모든 클래스가 공통으로 포함하고 있어야 하는 기능을 제공하기 위해서다.

Object는 모든 클래스의 최고 조상이기 때문에 Object클래스의 멤버들은 모든 클래스에서 바로 사용가능하다.

<br>

**1. toString**  
    
```java
    class Calculator{
        int left, right;
          
        public void setOprands(int left, int right){
            this.left = left;
            this.right = right;
        }
        public void sum(){
            System.out.println(this.left+this.right);
        }
          
        public void avg(){
            System.out.println((this.left+this.right)/2);
        }
         
        public String toString(){
            return "left : " + this.left + ", right : "+ this.right;
        }
    }
      
    public class CalculatorDemo {
          
        public static void main(String[] args) {
              
            Calculator c1 = new Calculator();
            c1.setOprands(10, 20);
    
            System.out.println(c1);
            System.out.println(c1.toString());
        }
    		
    		// 실행결과
    		// left : 10, right : 20
    		// left : 10, right : 20
      
    }
```
    
클래스 Calculator에 toString을 재정의(overiding)했다.  
그리고 인스턴스를 System.out.println의 인자로 전달하니까 toString을 명시적으로 호출하지 않았음에도 동일한 효과가 나고 있다.  
toString을 직접 호출하지 않아도 어떤 객체를 System.out.print로 호출하면 자동으로 toString이 호출되도록 약속되어 있기 때문이다!

<br>

**2. equals(Object obj)**
    
*유의사항  
   
1. 객체 간에 동일성을 비교하고 싶을 때는 ==를 사용하지 말고 equals를 이용하자.
    
2. equals를 직접 구현해야 한다면 hashCode도 함께 구현해야 함
    
3. equals를 직접 구현해야 한다면 eclipse와 같은 개발도구들은 equals와 hashCode를 자동으로 생성해주는 기능을 가지고 있다. 이 기능을 이용하는 것을 고려해보자.
    
4. 그 이유가 분명하지 않다면 비교 연산자 == 은 `원시 데이터형`을 비교할 때만 사용하자.
    
<aside>
💡 원시 데이터 형(Primitive Data Type)
    
자바에서 기본적으로 제공하는 데이터 타입  
byte, short, int, long, float, double, boolean, char가 있다.   
이러한 데이터 타입들은 new 연산자를 이용해서 생성하지 않아도 사용될 수 있다.
    
</aside>

<br>

**3. clone**
