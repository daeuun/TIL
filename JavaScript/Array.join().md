## Array.join()

배열의 원소를 연결하여 하나의 값으로 만들기

+ 원소들의 구분은 콤마(,)로 한다.  
+ 구분을 다른 문자로 하려면 () 안에 원하는 문자 넣기

```jsx
var userSeq = [];
$.each(chkList, function(i, data) {
	userSeq.push(data.USER_ID + data.YYMM + data.SEQ);
})
sources.USER_SEQ_LST = userSeq.join(",");

// 결과 : "user012021121,user022021121...."
```
