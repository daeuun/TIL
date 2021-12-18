## indexOf
호출한 String 객체에서 주어진 값과 일치하는 첫 번째 인덱스를 반환한다.  
일치하는 값이 없으면 -1 반환

```javascript
str.indexOf(searchValue[, fromIndex])
```

+ searchValue  
찾으려는 문자열.  
아무 값도 주어지지 않으면 문자열 "undefined"를 찾으려는 문자열로 사용


+ fromIndex  
문자열에서 찾기 시작하는 위치를 나타내는 인덱스 값.  
어떤 정수값이라도 가능  
기본값은 0이며, 문자열 전체를 대상으로 찾게 됩니다.  
만약 fromIndex 값이 음의 정수이면 전체 문자열을 찾게 됩니다.  
만약 fromIndex >= str.length 이면, 검색하지 않고 바로 -1을 반환합니다. searchValue가 공백 문자열이 아니라면, str.length를 반환합니다.

+ 반환 값  
searchValue의 첫 번째 등장 인덱스. 
찾을 수 없으면 -1.

```javascript
var anyString = 'Brave new world'; 

console.log('The index of the first w from the beginning is ' + anyString.indexOf('w')); // 첫번째 w 문자 위치는 8 
console.log('The index of the first w from the end is ' + anyString.lastIndexOf('w')); // 마지막 w 문자 위치는 10 

console.log('The index of "new" from the beginning is ' + anyString.indexOf('new')); // 첫번째 new 문자열 위치는 6 
console.log('The index of "new" from the end is ' + anyString.lastIndexOf('new')); // 마지막 new 문자열 위치는 6
```
