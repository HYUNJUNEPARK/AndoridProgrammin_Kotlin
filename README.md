#Permission

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/permission.png" height="400"/>

---
1. <a href = "#content1">퍼미션 설정과 사용 설정</a></br>
2. <a href = "#content2">퍼미션 함수</a></br>
3. <a href = "#content3">content3</a></br>
* <a href = "#ref">참고링크</a>
---
><a id = "content1">**1. 퍼미션 설정과 사용 설정**</a></br>

`<permission>` : 기능을 보호하려는 앱의 매니페스트 파일에 설정</br>
`<uses-permission>` : 퍼미션으로 보호된 기능을 사용하려는 앱의 매니페스트 파일에 설정</br>

<br></br>
**protectionLevel**</br>
**normal** : 낮은 수준의 보호. 사용자에게 권한을 요청하지 않아도 됨</br>
**dangerous** : 높은 수준의 보호. 사용자에게 권한 허용을 요청해야 함</br>
**signature** : 같은 키로 인증된 앱만 실행</br>
**signatureOrSystem** : AOS 앱이거나 같은 키로 인증한 앱만 실행</br>

<br></br>
**퍼미션 리스트**</br>
ACCESS_FINE_LOCATION: 정확한 위치 정보 액세스</br>
ACCESS_NETWORK_STATE: 네트워크에 대한 정보 액세스</br>
ACCESS_WIFI_STATE: 와이파이 네트워크에 대한 정보 액세스</br>
BATTERY_STATS: 배터리 통계 수집</br>
BLUETOOTH: 연결된 블루투스 장치에 연결</br>
BLUETOOTH_ADMIN: 블루투스 장치를 검색하고 페어링</br>
CALL_PHONE: 다이얼 UI를 거치지 않고 전화를 시작</br>
CAMERA: 카메라 장치에 액세스</br>
INTERNET: 네트워크 연결</br>
READ_CONTACTS: 사용자의 연락처 데이터 읽기</br>
READ_EXTERNAL_STORAGE: 외부 저장소에서 파일 읽기</br>
READ_PHONE_STATE: 장치의 전화번호, 네트워크 정보, 진행 중인 통화 상태 등 전화 상태에 대한 읽기</br>
READ_SMS: SMS 메시지 읽기</br>
RECEIVE_BOOT_COMPLETED: 부팅 완료 시 수행</br>
RECEIVE_SMS: SMS 메시지 수신</br>
RECORD_AUDIO: 오디오 녹음</br>
SEND_SMS: SMS 메시지 발신</br>
VIBRATE: 진동 울리기</br>
WRITE_CONTACTS: 사용자의 연락처 데이터 쓰기</br>
WRITE_EXTERNAL_STORAGE: 외부 저장소에 파일 쓰기</br>

<br></br>
<br></br>

><a id = "content2">**2. 퍼미션 함수**</a></br>

**퍼미션 허용 확인 함수**</br>
`context.checkSelfPermission(permission): Int`</br>
-사용자가 이미 앱에 특정권한을 부여했는지 확인하며 권한 여부에 따라 **PERMISSION_GRANTED**(0) 또는 **PERMISSION_DENIED**(-1) 를 반환</br>

**퍼미션 요청 함수**</br>
`ActivityCompat.requestPermissions(context, permissions, requestCode)`</br>
-사용자에게 권한 요청하는 팝업을 띄우고 사용자 응답을 `onRequestPermissionsResult()` 에서 처리</br>

<br></br>
<br></br>
---

><a id = "ref">**참고링크**</a></br>

Android Open App Permission Screen Programmatically</br>
https://stackoverflow.com/questions/32822101/how-can-i-programmatically-open-the-permission-screen-for-a-specific-app-on-andr</br>
https://code.luasoftware.com/tutorials/android/android-open-app-permission-screen-programmatically/</br>

시스템 퍼미션 목록 공식 문서</br>
https://developer.android.com/reference/android/Manifest.permission.html</br>
