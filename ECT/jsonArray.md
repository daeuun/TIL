## JSON형식

`"key-value"`가 쌍으로 이루어진 데이터들의 집합

```jsx
// JSON 기본 구조
"key" : "value"

// JSON OBJECT 구조
{
    "USER_ID" : "IMUSER",
    "PASSWORD" : "PWD123"
}

// JSON 배열[] 구조
fruitArray = [{
	"FRUIT_NM" : "APPLE",
	"COUNT" : "12"
}, {
	"FRUIT_NM" : "BANANA",
	"COUNT" : "30"
}]

// JSON 배열에서 값 가져오기
for(var i in fruitArray){
	var result = fruitArray[i]; 
	console.log(result.FRUIT_NM); 
	// APPLE
	// BANANA
}
```

1. 서로 다른 `key-value`값은 `comma(,)`로 연결한다.
2. 한 객체의 여러 정보를 하나로 묶을 때 `'{ }'`를 사용하여 처리한다.
3. 한 개 이상의 key-value들이 중괄호`{}`로 묶여진 구조를 `객체(Object)`라고 부른다.