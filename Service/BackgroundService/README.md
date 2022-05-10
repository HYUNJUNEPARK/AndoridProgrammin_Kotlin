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
-서비스를 실행하려면 시스템에 인텐트를 전달해야하며 사용하는 함수는 startService(), bindService() 두가지가 있음</br>
-명시적 인텐트로 실행하려면 Manifest 에 클래스명만 등록하고, 암시적 인텐트로 실행하려면 intent-filter 를 등록해야 함</br>
-서비스를 종료하려면 `stopService()` 함수로 인텐트를 전달함

실행 방식 분류 (1)스타티드 서비스 (2)바운드 서비스</br>
실행 구조 분류 (1)백그라운드 서비스 (2)포어그라운드 서비스</br>

**스타티드 서비스(Started Service)**</br>
-액티비티와 상관없이 독립적으로 동작하며 액티비티 종료와 무관하게 동작하므로 장시간 백그라운드 처리를 할 때 사용</br>
-해당 서비스를 인텐트에 담아서 매개변수로 전달
-startService(Intent service) 로 호출</br>
-stopService(Intent name) 로 서비스 종료</br>
-외부 앱의 서비스라면 암시적 인텐트로 실행해야 하므로  `setPackage()` 함수를 이용해 앱의 패키지명을 명시해 줌</br>
하지만 외부 앱이 백그라운드 상태라면 서비스를 실행할 수 없음</br>

```kotlin
//암시적 인텐트로 실행
val intent = Intent("ACTION_OUTER_SERVICE")
intent.setPackage("com.example.test_outter")
startService(intent)

//명시적 인텐트로 서비스 실행
val intent = Intent(this, MyService::class.java)
startService(intent)

//서비스 종료
val intent = Intent(this, MyService::class.java)
stopService(intent)
```

**바운드 서비스(Bound Service)**</br>
-액티비티와 값을 주고 받을 때 사용하며 값을 주고 받기 위한 인터페이스 제공</br>
-연결된 액티비티가 종료되면 서비스도 종료되고 특별한 경우를 제외하고는 잘 사용안됨</br>
-여러개의 액티비티가 같은 서비스를 사용할 수 있어 기존에 있는 서비스를 바인딩해 재사용 가능</br>
-ServiceConnection 인터페이스를 구현한 객체를 준비해야 함</br>
-bindService(Intent service, ServiceConnection conn, int flags) 로 호출</br>

```kotlin
//ServiceConnection 인터페이스 구현
val connection: ServiceConnection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) { } //bindService() 로 서비스를 구동할 때 자동 호출
    override fun onServiceDisconnected(name: ComponentName?) { } //unbindService() 로 서비스를 종료할 때 자동 호출
}

//암시적 인텐트로 실행
val intent = Intent("ACTION_OUTER_SERVICE")
intent.setPackage("com.example.test_outter")
bindServiceService(intent, connection, Context.BIND_AUTO_CREATE)

//명시적 인텐트로 서비스 실행
val intent = Intent(this, MyService::class.java)
bindServiceService(intent, connection, Context.BIND_AUTO_CREATE)

//서비스 종료
unbindService(connection)
```













**스레드(서비스와 비교할 개념)**</br>
-하나의 프로세스 상의 독립적인 실행 흐름</br>
-액티비티 생명주기에서 실행되며 액티비티가 종료될 때 종료됨</br>
<br></br>
<br></br>

><a id = "content2">**2. 스타티드 서비스**</a></br>

```kotlin
//1.서비스 호출
//startService(intent) - MainActivity
binding.startButton.setOnClickListener {
    val intent = Intent(this, StartedService::class.java)
    intent.action = StartedService.ACTION_START
    startService(intent)
}


//2.서비스 동작
//onStartCommand() - StartedService
override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    val action = intent?.action
    Log.d("Service", "[StartedService : action] : $action")
    return super.onStartCommand(intent, flags, startId)
}


//3.서비스 종료
//stopService(intent) - MainActivity
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
