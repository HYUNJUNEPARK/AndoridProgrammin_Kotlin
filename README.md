# Thread

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/thread.jpg" height="400"/>

---
1. <a href = "#content1">스레드</a></br>
2. <a href = "#content2">스레드 사용 방법</a></br>
3. <a href = "#content3">루퍼/핸들러</a></br>
* <a href = "#ref">참고링크</a>
---
><a id = "content1">**1. 스레드**</a></br>


**프로세스**</br>
실행 중인 프로그램. 독립된 메모리 공간을 할당받음</br>

**스레드**</br>
하나의 프로세스상에 독립적인 실행 흐름</br>
하나의 프로세스에는 메인스레드만 있거나 메인스레드 + 서브스레드로 구성</br>

**(a) 메인스레드(UI 스레드)**</br>
 -UI 조작은 메인스레드만 가능</br>
 -UI 이벤트 및 작업이 오래 걸린다면 AOS 는 ANR 오류 발생시킴</br>
 -하나의 앱에 하나만 존재</br>

**(b) 백그라운드스레드(서브 스레드)**</br>
 -메모리 외의 다른 곳에서 데이터를 가져오는 작업 권장</br>
 -처리 시간을 미리 계산할 수 없는 작업의 처리 권장</br>
 -하나의 앱에 여러개 존재 가능</br>
 -runOnUiThread { ... } 로 UI 컴포넌트에 접근</br>

-> **이러한 멀티 스레드 환경에서 동기화 이슈를 차단하고 스레드간의 통신을 위해 사용되는 것이 핸들러와 루퍼**</br>


<br></br>
<br></br>

><a id = "content2">**2. 스레드 사용 방법**</a></br>


(1) Thread 클래스를 상속받아 스레드 생성</br>

```kotlin
class WorkerThread : Thread() { //Thread 클래스를 상속은 클래스 생성
    override fun run() { //run 블럭에 스레드가 처리할 로직을 정의
        var i = 0
        while (i < 10) {
            i += 1
            Log.i("WorkerThread", "$i")
        }
    } //run()의 실행이 끝나면 스레드는 종료
}

var wThread = WorkerThread() //Thread 객체를 만들어 별도의 스레드를 생성
wThread.start() //start()를 호출하면 run()에서 정의된 로직 실행
```
<br></br>
(2) Runnable 인터페이스를 구현하고 스레드 생성</br>

```kotlin
class WorkerRunnable : Runnable {
    override fun run() {
        var i = 0
        while (i < 10) {
            i += 1
            Log.i("WorkerRunnable", "$i")
        }
    }
}

var rThread = Thread(WorkerRunnable()) //Thread 클래스의 생성자로 WorkerRunnable() 전달
rThread.start()
```
<br></br>
(3) thread()에 start=true 를 전달</br>

```kotlin
thread(start=true) {
    var i = 0
    while (i < 10) {
        i += 1
        Log.i("KotlinThread", "$i")
    }
}
```
<br></br>
(4) 람다로 스레드 사용</br>

```kotlin
Thread {
    var i = 0
    while (i < 10) {
        i += 1
        Log.i("LambdaThread", "$i")
    }
}.start()
```

<br></br>
<br></br>

><a id = "content3">**3. 루퍼/핸들러**</a></br>



**루퍼(Looper)**</br>
-메인액티비티가 실행됨과 동시에 for 문 하나가 무한루프를 돌고 있는 서브 스레드로 같은 작업 반복</br>
-Message Queue 에 쌓인 메시지와 Runnable 객체를 차례로 핸들러에 전달</br>
-하나의 스레드에는 오직 하나의 루퍼를 가지며, 루퍼는 오직 하나의 스레드를 담당한다</br>
-동시다발적으로 메세지가 발생할 수 있기 때문에 메시지 큐에 쌓았다가 처리</br>

**핸들러**</br>
-받은 메세지를 처리하고 스레드간의 메세지를 전달하는 작업을 수행</br>
-백그라운드 스레드와 메인스레드를 연결</br>
-핸들러를 생성하면 핸들러는 생성한 스레드와 루퍼에 연결된다</br>
-루퍼는 앱이 실행되면 자동으로 하나 생성되지만 핸들러는 개발자가 직접 작성해야함</br>


**메시지 큐(Message Queue)**</br>
-다른 스레드 또는 자신으로 부터 전달 받은 메시지를 보관하는 Queue</br>


<br></br>
<br></br>
---

><a id = "ref">**참고링크**</a></br>
Thread/Handler/Looper</br>
https://velog.io/@dev_jin/Android%EC%95%88%EB%93%9C%EB%A1%9C%EC%9D%B4%EB%93%9C-ThreadHandlerLooper-5v6by33k</br>
