## data

HTML5부터는 데이터 속성이라는 개념이 생겼습니다. 데이터 속성은 HTML 요소의 'data-' 로 시작하는 속성입니다. 이러한 데이터 속성은 특정한 데이터를 DOM 요소에 저장해두기 위함이 목적입니다.  

데이터 속성은 'data-' 로 시작해야 하며, 브라우저는 이러한 데이터 속성에는 어떠한 행동도 관여하지 않기 때문에 개발자는 요소에 특정한 데이터를 저장하고 싶은 경우 자유롭게 사용할 수 있습니다.  


예를 들어 다음 input 태그에는 data-value 라는 데이터 속성을 제 임의로 만들었습니다. 속성명은 data-value이지만 HTML 표준 속성인 value 속성과는 상관이 없습니다. 제가 임의로 만든 속성이기 때문이죠.

```
<input type="text" data-value="001" id="username">                                    
```





## 장점

데이터 속성의 장점은 이전과 같이 hidden으로 태그를 숨겨두고 데이터를 저장할 필요가 없어졌다는 점 입니다. 따라서 훨씬 HTML 스크립트가 간결해집니다. 또한 하나의 HTML 요소에는 여러 데이터 속성을 동시에 사용할 수도 있습니다.
```
<input type="text" data-value="001"  data-code="c03"  id="username">                 
```



## data 속성 조작하기

자바스크립트에서 데이터 속성을 조작하기 위한 방법은 여러가지가 있습니다만 기본적으로 DOM 객체를 통해 데이터 속성 조작이 가능합니다. 

```
<input type="text" data-value="001"  data-code="c03"  id="username">            
```



데이터속성은 DOM 객체의 dataset 속성을 참조하여 얻을 수 있습니다. 다음과 같이 username input을 셀렉터로 잡고 해당 DOM 객체의 dataset을 출력해 보았습니다.

```
var input = document.querySelector('#username');                                     

console.log(input.dataset);
```


아래와 같이 DOMStringMap으로 데이터 속성들을 가지고 있음을 확인할 수 있습니다. 그러나 속성 key 값을 보면 'data-' 가 빠진 채로 존재한다.
