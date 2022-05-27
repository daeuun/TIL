1. String.valueOf()  
    파라미터가 null이면 문자열 "null"을 만들어서 반환한다.
    
2. Object.toString()  
    대상 값이 null이면 NPE를 발생시키고 Object에 담긴 값이 String이 아니여도 출력한다.
    

두가지 메서드의 차이점은 null값에 따른 NPE의 발생 유무이다.  
이런 차이점 때문에 valueOf의 null체크 방법은 "null".equals(string) 형태로 체크를 해야한다.  

null로 인해 발생된 에러는 시간이 지나고, 타인의 소스인 경우 디버깅하기 어렵고 어떤 의미를 내포하고 있는지 판단하기 어렵다.  
때문에 NPE를 방지하기 위해 toString보다는 valueOf를 사용하는 것을 추천한다.
