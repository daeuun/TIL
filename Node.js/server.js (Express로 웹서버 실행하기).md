## **Express**

node.js로 서버를 만드는 framework

<br>

### 콘솔에 `npm install express --save` 를 입력해서 express 서버를 설치한다.

`-save` 옵션 : node 프로젝트가 의존하고 있는 외부 라이브러리 정보를 package.json 안에 넣는 명령어

<br>

```jsx
// 기본 설정
const express = require('express');
const app = express();

const server = app.listen(3000, () => {
    console.log('Start Server : localhost:3000');
});

var mysql = require('mysql');
var pool = mysql.createPool({
    connectionLimit: 10,
    host: '',
    user: '',
    password: '',
    database: ''
});

// 정적 파일 불러오기
// 현재 디렉토리에서 views폴더를 가져올것임
app.set('views', __dirname + '/views');
app.set('vidw engine', 'ejs'); // ejs: 임베디드 자바스크립트 템플릿 html에서 자바스크립트 쓸수있게 해주는거
app.engine('html', require('ejs').renderFile);

// 클라이언트에서 서버로 request
// 서버에서 클라이언트로 response
// 서버에 어떤 기능과 매핑해줄지 결정하는것 라우팅한다라고 함 => 라우터를 정해줘야함 

// 라우팅 정의
app.get('/', function (req, res) {
    res.render('index.html')
})

app.get('/about', function (req, res) {
    res.render('about.html')
})

// db에 직접 연결해서 불러오기
app.get('/db', function (req, res) {
    pool.getConnection(function(err, connection) {
        if (err) throw err; // not connected!
       
        // Use the connection
        connection.query('SELECT * FROM Test', function (error, results, fields) {
          res.send(JSON.stringify(results));
          console.log('results', results);
            // When done with the connection, release it.
          connection.release();
       
          // Handle error after the release.
          if (error) throw error;
       
          // Don't use the connection here, it has been returned to the pool.
        });
      });
})
```