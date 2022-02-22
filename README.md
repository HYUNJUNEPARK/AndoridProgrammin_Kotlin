# Foreground Service

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/foregroundService.jpg" height="400"/>

---
1. <a href = "#content1">포어그라운드 서비스</a></br>
* <a href = "#ref">참고링크</a>
---
><a id = "content1">**포어그라운드 서비스**</a></br>


-알림을 통해 현재 작업이 진행중이라는 것을 알려줘야함</br>
-백그라운드 서비스는 앱이 꺼지거나 가용 자원이 부족하면 시스템에 의해 제거될 수 있지만 포어그라운드 서비스는 종료되지 않음</br>


```kotlin
//Manifest
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>


//상단바에 알람을 띄울 때 앱이 사용할 채널 설정 - ForegroundService
private val CHANNEL_ID = "ForegroundChannel"


//알림을 생성하는 메서드 - ForegroundService
//오레오 버전 부터는 모든 알림은 채널 단위로 동작함
private fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val serviceChannel = NotificationChannel(CHANNEL_ID,"Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }
}


//onStartCommand() 를 오버라이딩 해 알림 제목, 아이콘 등 알림 생성 - ForegroundService
override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    createNotificationChannel()
    val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
        .setContentTitle("Foreground Service")
        .setSmallIcon(R.mipmap.ic_launcher_round)
        .build()
    startForeground(1, notification)
    return super.onStartCommand(intent, flags, startId)
    }
}


//ContextCompat.startForegroundService() 에 인텐트를 전달해 알림 실행 - MainActivity
val intent = Intent(this, ForegroundService::class.java)
ContextCompat.startForegroundService(this, intent)


//stopService() 에 인텐트를 전달해 알림 종료 -MainActivity
val intent = Intent(this, ForegroundService::class.java)
stopService(intent)

```
<br></br>
<br></br>

---

><a id = "ref">**참고링크**</a></br>


