# Foreground Service

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/foregroundService.jpg" height="400"/>

---
1. <a href = "#content1">포어그라운드 서비스</a></br>
* <a href = "#ref">참고링크</a>
---
><a id = "content1">**포어그라운드 서비스**</a></br>

-알림을 통해 현재 작업이 진행중이라는 것을 알려줘야함</br>
-앱이 꺼지거나 가용 자원이 부족하면 백그라운드 서비스는 시스템에 의해 제거될 수 있지만</br>
포어그라운드 서비스는 사용자가 알림을 통해 서비스가 동작하고 있다는 것을 인지하고 있기 때문에 가용 자원 부족 이유로는 종료되지 않음

<br></br>


```kotlin
//Manifest
<uses-permission android:name="android.permission.FOREGROUND_SERVICE"/>


//상단바에 알람을 띄울 때 앱이 사용할 채널 설정 - ForegroundService
private val CHANNEL_ID = "ForegroundChannel"


//알림 생성 메서드 - ForegroundService
private fun createNotificationChannel() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { //오레오 버전 부터는 모든 알림은 채널 단위로 동작함
        val serviceChannel
            = NotificationChannel(CHANNEL_ID,"Foreground Service Channel", NotificationManager.IMPORTANCE_DEFAULT)
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(serviceChannel)
    }
}


//onStartCommand() 오버라이딩 - ForegroundService
override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
    createNotificationChannel()
    val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID) //채널 지정, 알림 제목, 아이콘 등 알림 설정
        .setContentTitle("Foreground Service")
        .setSmallIcon(R.mipmap.ic_launcher_round)
        .build()
    startForeground(1, notification)
    return super.onStartCommand(intent, flags, startId)
}


//포어그라운드 서비스 시작
//ContextCompat.startForegroundService() 에 인텐트를 전달 - MainActivity
val intent = Intent(this, ForegroundService::class.java)
ContextCompat.startForegroundService(this, intent) //


//포어그라운드 서비스 종료
//stopService() 에 인텐트를 전달해 알림 종료 - MainActivity
val intent = Intent(this, ForegroundService::class.java)
stopService(intent)

```
<br></br>
<br></br>

---

><a id = "ref">**참고링크**</a></br>


