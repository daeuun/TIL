## event:init

페이지 로딩 시 발생하는 이벤트

초기로딩 시 database에서 정보를 조회하여 값을 설정하는 경우 사용

```java
<select data-init="웅앵"></select>
```

<br>

```java
<body onload="alert('안녕!');">
```

⇒ init 함수를 선언한 뒤에, 그 함수를 호출

```java
<script language="javascript">  
	function init() {    
		alert("안녕!");  
	}
</script>

<body onload="init();">
```

<br>

## 사용이유

모듈화!

초기화 할 변수를 함수에 넣고 함수를 호출하면 간단하게 초기화가 가능하다.

간단한 코드인 경우에는 큰 차이가 없지만 반복적으로 호출하는 경우나 다른 곳에서 사용하게 되는 경우 효과적