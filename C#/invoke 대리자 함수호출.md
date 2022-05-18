기본적으로 하나의 응용프로그램은 하나의 단일 스레드로 구성되어 있기 때문에
for문이나 whlie같은 반복문의 처리가 길어질 경우 사용자는 다른 작업을 진행 할 수 없다.

그래서 스레드를 추가로 생성하여 긴 시간이 소요되는 작업들을 분리하여 처리하면 된다.  

스레드를 생성하는 것은 매우 쉬운데,  
예를 들어 버튼 클릭에 스레드 생성 이벤트를 주면 쉽게 스레드를 생성하여 사용할 수 있다.

```c#
using System.Threading;

private void button_click (obiect sender: EventArgs e)
{
    // 생성자 안의 SearchThread는 사용자가 스레드로 처리할 함수 이름
    Thread mThread = new Thread(SearchThread);
    mThread.Start();
}

private void SearchThread()
{
    // 함수의 내용 정의
}
```

주의할 점은 스레드로 실행되는 함수 내에서 폼의 컨트롤들(버튼이나, 콤보박스 등)의 속성(Value, 위치, Index)을 변경하려고 하면 Cross Thread 오류가 나게 된다. 
서로 다른 스레드에서 UI를 호출 할 수 없다. 
그래서 대리자를 사용한 Invoke를 사용하여 별도의 함수에서 처리하도록 한다.

```c#
using System.Threading;

// 함수의 대리자 : 호출될 함수와 반환타입과 파라미터 타입/개수가 같아야 한다
private delegate void UpdateCbBox(int nlndex);
private void UpdatecbBoxl(int nlndex) // 실제 호출할 함수
{
    comboBox1.SeletedIndex = nlndex;
}

private void button_click (object sender, EVentArgs e)
{
    // 생성자 안의 SearchThread는 사용자가 스레드로 처리할 함수 이름
    Thread mThread = new Thread(SearchThread);
    mThread.Start(); // 스레드 시작
}

private void SearchThread()
{
    // 함수의 내용 정의
    this.Invoke(new UpdatecbBox(UpdateCbBoxl), new object[] {1}); // {1}은 함수에 넘길 파라메터 값
}
```

위와 같이 invoke를 하게되면 다른 스레드에서 UI의 상태를 변경할 수 있다.
스레드 생성자에는 인수를 넘길 수 없다.
private void SearchThread(int a) 이런식으로 사용할 수 없다.
