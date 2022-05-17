### select박스의 onchange에서 this.value 사용하기

```jsx
<select class="classlist" name="methodname" data-target="prototype" onchange="this.nextElementSibling.value=this.options[this.selectedIndex].text"></select>
<input type="text" id="txtMethod" value="" />
```

목표 : select에서 선택한 text ⇒ input의 value 값으로 넣어주기

- this.`nextElementSibling`
    
    바로 다음 형제 요소
    
- this.options[this.selectedIndex].text
    
    `selectedIndex` : 선택된 인덱스의 
    
    `options` : option 요소들에 접근해서 
    
    text를 찾는다.
    
    *그럼 어제 this.text로 select option의 text값 못찾은 이유는 option에 접근해서 값을 가져온게 아니라서 그런건가?*
    
<br>

*+연관된 수정 (prototype textarea)*

선택된 option의 값에 따라 prototype을 textarea에 뿌려주는 일을 해야하는데

selectedIndex를 모르고 반복문을 돌려서 찾으려고 했다.

selectedIndex로 위치값 가져오면 바로 알 수 있는데....

```jsx
// Method 콤보박스 변경시 prototype 변경
$("select[name=methodname]").change(function () {
    $('.textarea_prototype').val(option[this.selectedIndex]);
});
```

필요없는 반복문이었어..

```jsx
$("select[name=methodname]").change(function () {
    var methodVal = parseInt($(this).val());
    for (var i = 0; i < option.length; i++) {
        if (methodVal == i) {
            $('.textarea_prototype').val(option[i]);
            break;
        }
    }
});
```
