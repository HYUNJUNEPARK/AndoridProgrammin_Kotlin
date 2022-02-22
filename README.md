<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/backgroundService.jpg" height="400"/>

---
1. <a href = "#content1">서비스 개념</a></br>
2. <a href = "#content2">스타티드 서비스</a></br>
* <a href = "#ref">참고링크</a>

---
><a id = "content1">**1. 서비스 개념**</a></br>



**서비스**</br>
-메인 스레드에서 동작하는 화면이 없는 컴포넌트로 백그라운드 코드 처리 담당</br>
-안드로이드 컴포넌트(액티비티, 브로드캐스트 리시버, 서비스, 콘텐트 프로바이더)</br>
-백그라운드에서 실행되며 액티비티 생명주기와 독립적인 자체 생명주기를 갖는다</br>
-액티비티가 종료될 때 명시적으로 종료시키지 않으면 서비스는 계속 실행됨</br>

실행 방식 분류 (1)스타티드 서비스 (2)바운드 서비스</br>
실행 구조 분류 (1)백그라운드 서비스 (2)포어그라운드 서비스</br>

**스타티드 서비스(Started Service)**</br>
-액티비티와 상관없이 독립적으로 동작하며 액티비티 종료와 무관하게 동작하므로 장시간 백그라운드 처리를 할 때 사용</br>
-startService(Intent service) 로 호출</br>
-stopService(Intent name) 로 서비스 종료</br>

**바운드 서비스(Bound Service)**</br>
-액티비티와 값을 주고 받을 때 사용하며 값을 주고 받기 위한 인터페이스 제공</br>
-연결된 액티비티가 종료되면 서비스도 종료되고 특별한 경우를 제외하고는 잘 사용안됨</br>
-여러개의 액티비티가 같은 서비스를 사용할 수 있어 기존에 있는 서비스를 바인딩해 재사용 가능</br>
-bindService(Intent service, ServiceConnection conn, int flags) 로 호출</br>

**스레드(서비스와 비교할 개념)**</br>
-하나의 프로세스 상의 독립적인 실행 흐름</br>
-액티비티 생명주에서 실행되며 액티비티가 종료될 때 종료됨</br>
<br></br>
<br></br>

><a id = "content2">**2. 스타티드 서비스**</a></br>

```kotlin
//1.서비스 호출 startService(intent) - MainActivity
binding.startButton.setOnClickListener {
    val intent = Intent(this, StartedService::class.java)
    intent.action = StartedService.ACTION_START
    startService(intent)
}


//2.서비스 동작 onStartCommand() - StartedService
override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    val action = intent?.action
    Log.d("Service", "[StartedService : action] : $action")
    return super.onStartCommand(intent, flags, startId)
}


//3.서비스 종료 stopService(intent) - MainActivity
binding.stopButton.setOnClickListener {
    val intent = Intent(this, StartedService::class.java)
    stopService(intent)
}


```
<br></br>
<br></br>
---

><a id = "ref">**참고링크**</a></br>

서비스 vs 스레드</br>
https://onlyfor-me-blog.tistory.com/393</br>
