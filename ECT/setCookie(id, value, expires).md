## setCookie

자바스크립트에서 쿠키 세팅할 때 expires을 일수로 설정할수 있는 함수

<br>

```jsx
setCookie('cookie_name', 'cookie_value', 10);  //10일 후 만료
var str = getCookie('cookie_name');

// 쿠키저장
setCookie('cookie_name','cookie_value', 1, "/");

// 쿠키값 얻기
getCookie('cookie_name');

// 쿠키삭제
deleteCookie('cookie_name');
```

```jsx
// 쿠키값 설정
function setCookie(name, value, days) {
        if (days) {
                var date = new Date();
                date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
                var expires = "; expires=" + date.toGMTString();
        } else {
               var expires = "";
        }

        document.cookie = name + "=" + value + expires + "; path=/";
}

// 쿠키값 가져오기
function getCookie(name) {
        var i, x, y, ARRcookies = document.cookie.split(";");

        for (i = 0; i < ARRcookies.length; i++) {
                x = ARRcookies[i].substr(0, ARRcookies[i].indexOf("="));
                y = ARRcookies[i].substr(ARRcookies[i].indexOf("=") + 1);

                x = x.replace(/^\s+|\s+$/g, "");

                if (x == name) {
                        return unescape(y);
                }
        }
}
```