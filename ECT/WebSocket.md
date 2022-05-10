### 웹소켓(WebSocket)의 배경

: 인터넷이 나오고 HTTP를 통해서 서버로부터 데이터를 가져오기 위해서는 오로지 URL을 통한 요청이 유일한 방법이었습니다. 때문에 아이디 중복 확인과 같은 유효성 검사는 서버로 데이터를 보내는 중간과정에서 새로운 페이지 요청을 하게 되었습니다.

 

여기서 발전된 방식이 Ajax통신으로 클라이언트에서 XMLHttpRequest 객체를 이용하여 서버에 요청을 보내면 서버가 응답을 하는 방식입니다.

페이지 요청이 아닌 데이터 요청이라 부분적으로 정보를 갱신할 수 있게 됩니다.

즉, 사용자의 이벤트로부터 Javascript는 사용자가 작성한 값이 쓰여진 DOM을 읽습니다.

그리고 XMLHttpRequest 객체를 통해 웹서버에 해당 값을 전송하고 웹서버는 요청을 처리하고 XML, Text, JSON 등을 이용하여 XMLHttpRequest 객체에 전송합니다.

그런다음 JavaScript가 해당 응답정보를 DOM에 쓰여집니다.

Ajax를 사용하면 새로운 HTML을 서버로부터 받아야하는 것이 아닌 동일한 페이지의 일부를 수정할 수 있는 가능성이 생기고, 사용자 입장에서는 페이지 이동이 발생되지 않고 페이지 내부 변화만 일어나게 해주므로 그만큼의 자원과 시간을 아낄수 있습니다.

 

하지만, Ajax도 결국 HTTP를 이용하기 때문에 요청을 보내야 응답이 옵니다. 

변경된 데이터를 가져오기 위해서 버튼을 누른다거나 일정 시간 주기로 요청을 보낸다면 번거로울 뿐더러 자원 낭비입니다.

예를 들어 주식에서 기업의 주가를 보려면 매번 버튼을 갱신해서 요청을 하고 서버는 이에 응답을 해주는 것 입니다.

 

이러한 문제들을 해결하기 위해 웹소켓이 탄생합니다.

 

 


 

### 웹소켓(WebSocket)

: 2014년 10월 28일의 HTML5 버전이 나올 때 함께 등장했던 웹소켓은 2016년 11월 1일 HTML5.1버전이 나오고

2017년 12월 14일 HTML5.2버전이 나올때까지 지속적으로 발전해나아갔습니다.

 

웹소켓은 HTTP와 같이 약속입니다. 

(HTTP의 개념을 모르신다면, choseongho93.tistory.com/164?category=803676 참고해주세요.)

 

Transport protocol의 일종으로 서버와 클라이언트 간의 효율적인 양방향 통신을 실현하기 위한 구조입니다.

웹소켓은 단순한 API로 구성되어있으며, 웹소켓을 이용하면 하나의 HTTP 접속으로 양방향 메시지를 자유롭게 주고받을 수 있습니다.

 

위 배경에서 웹소켓이 나오기 이전에는 모두 클라이언트의 요청이 없다면, 서버로부터 응답을 받을 수 없는 구조였습니다.

웹소켓은 이러한 문제를 해결하는 새로운 약속이었습니다.

웹소켓에서는 서버와 브라우저 사이에 양방향 소통이 가능합니다. 브라우저는 서버가 직접 보내는 데이터를 받아들일 수 있고, 사용자가 다른 웹사이트로 이동하지 않아도 최신 데이터가 적용된 웹을 볼 수 있게 해줍니다. 웹페이지를 ‘새로고침’하거나 다른 주소로 이동할 때 덧붙인 부가 정보를 통해서만 새로운 데이터를 제공하는 웹서비스 환경의 빗장을 본질적으로 풀어준 셈입니다.

 

웹에서도 채팅이나 게임, 실시간 주식차트와 같은 실시간이 요구되는 응용프로그램의 개발을 한층 효과적으로 구현할 수 있게 됩니다.

가상화폐의 분산화 기술의 핵심도 WebSocket으로 구현할 수 있습니다.

 

### 작동원리

서버와 클라이언트간의 웹소켓 연결을 HTTP프로토콜을 통해 이루어집니다.

연결이 정상적으로 이루어진다면 서버와 클라이언트 간에 웹소켓 연결(TCP/IP기반)이 이루어지고 일정 시간이 지나면

HTTP연결은 자동으로 끊어집니다.

기본적으로  웹소켓 API는 아주 간단한 기능들만을 제공하기 때문에 대부분의 경우 SockJS나 Socket.IO같은 오픈 소스 라이브러리를 많이 사용하고 있으며 메시지 포맷 또한 STOMP같은 프로토콜을 같이 이용합니다.

 


### 문제점

1. 프로그램 구현에 보다 많은 복잡성을 초래합니다.

- 웹 소켓은 HTTP와 달리 Stateful protocol이기 때문에 서버와 클라이언트 간의 연결을 항상 유지해야 하며 만약 비정상적으로 연결이 끊어졌을때 적절하게 대응해야 한다. 이는 기존의 HTTP 사용시와 비교했을때 코딩의 복잡성을 가중시키는 요인이 될 수 있습니다.

2. 서버와 클라이언트 간의 Socket 연결을 유지하는 것 자체가 비용이 듭니다.

- 특히나 트래픽 양이 많은 서버같은 경우에는 CPU에 큰 부담이 될 수 있습니다.

3. 오래된 버전의 웹 브라우저에서는 지원하지 않습니다. (물론 SockJS 라이브러리 같은 경우에는 Fallback option을 제공하고 있습니다.)

 

### 대표적인 사용 예

1. 페이스북과 같은 SNS APP

2. LOL 같은 멀티플레이어 Game

3. 위치 기반 APP

4. 증권 거래 정보 사이트 및 APP

5. 화상 채팅 APP

6. 구글 Doc 같이 여러 명이 동시 접속해서 수정할 수 있는 Tool