# NetworkConnection

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/networkConnection1.png" height="400"/>
<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/networkConnection2.png" height="400"/>

```
//permission
<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<uses-permission android:name="android.permission.INTERNET"/>
```
<br></br>
<br></br>

><a id = "ref">**참고링크**</a></br>

네트워크 상태 읽기</br>
https://developer.android.com/training/basics/network-ops/reading-network-state?hl=ko</br>
<br></br>

NetworkCapabilities</br>
https://developer.android.com/reference/android/net/NetworkCapabilities</br>
<br></br>

연결 상태 및 연결 측정 모니터링</br>
https://developer.android.com/training/monitoring-device-state/connectivity-status-type?hl=ko</br>
<br></br>

네트워크 연결상태 체크하기</br>
https://teamblog.tistory.com/48</br>
<br></br>

WifiManager - Wifi 연결 액티비티 오픈</br>
https://developer.android.com/reference/android/net/wifi/WifiManager</br>
<br></br>

Intent used to turn on Mobile data? -> 데이터 사용 액티비티 오픈</br>
https://stackoverflow.com/questions/11484436/intent-used-to-turn-on-mobile-data</br>
```
val intent = Intent()
intent.component = ComponentName(
    "com.android.settings",
    "com.android.settings.Settings\$DataUsageSummaryActivity"
)
```

<br></br>
by using intent how we got mobile network setting page through in android -> 설정-연결 액티비티 오픈</br>
https://www.codeproject.com/Questions/568759/byplususingplusintentplushowplusweplusgotplusplusm</br>
```
Intent intent = new Intent(android.provider.Settings.ACTION_WIRELESS_SETTINGS);
startActivity(intent);
```