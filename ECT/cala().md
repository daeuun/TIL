## calc()
CSS 속성의 값으로 계산식을 지정할 수 있다.

```css
width: calc(100% - 80px);
```

**`+`**

덧셈.

`-`

뺄셈.

`*`

곱셈. 하나 이상의 피연산자가 number 이어야 함

`/`

나눗셈. 오른쪽 피연산자는 number 이어야 함

- +와 - 연산자는 좌우에 공백이 있어야 함

<br>

## 요소를 화면에 여백과 함께 배치하기

`고정된 너비의 여백`을 가진 요소 배치

```css
.banner {
  position: absolute;
  left: 40px;
  width: calc(100% - 80px);
  border: solid black 1px;
  box-shadow: 1px 2px;
  background-color: yellow;
  padding: 6px;
  text-align: center;
  box-sizing: border-box;
}
```

```html
<div class="banner">고정된 여백을 가진 배너</div>
```
