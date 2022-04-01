파일 저장 방법
(a) java.io 제공하는 File 클래스 이용
(b) Context 객체가 제공하는 openFileOutput(), openFileInput() 이용


File : 파일 및 디렉터리를 지칭하는 클래스
FileInputStream / FileOutputStream : 파일에서 바이트 스트림으로 데이터를 읽거나 쓰는 클래스
FileReader / FileWriter : 파일에서 문자열 스트림으로 데이터를 읽거나 쓰는 클래스

안드로이드 파일 저장소
(a) 내장 메모리 : 민감한 데이터 저장(단, 용량이 작음)
-앱별 저장소
(b) 외장 메모리 : 용량이 큰 데이터 저장
-앱별 저장소 : 다른 앱 접근 불가. 외부 앱이 접근하려면 파일 프로바이더로 공개하야함
-공용 저장소 : 다른 앱도 접근 가능


매니페스트 설정
-외장 메모리 파일 이용 시 필요한 퍼미션
`<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>`
`<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>`
# File Api 를 이용한다면 안드로이드 10 버전부터
 `<application ... android:requestLegacyExternalStorage="true"` />` 가 필요
-ContentResolver 에서 제공하는 InputStream 등을 사용한다면 퍼미션 설정 필요없음


```
//외장 메모리 사용 가능 여부 확인
if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
    //TODO ExternalStorageState MOUNTED : 외장 메모리 사용 가능
}
else {
    //TODO ExternalStorageState UNMOUNTED
}
```
`val file: File? = getExternalFilesDir(/*type*/)`
type 으로 가능한 파라미터
-null : /storage/emulated/0/Android/data/패키지명/files
-Environment.DIRECTORY_PICTURES : /storage/emulated/0/Android/data/패키지명/files/Pictures
외 다수
```

//외장 메모리 앱별 저장소에 파일 쓰기와 읽기
//파일 쓰기
val file:File = File(getExternalFilesDir(null), "test.txt") //외부저장소 디렉터리에 test.txt 생성
val writeStream: OutputStreamWriter = file.writer()
writeStream.write("Stream Test")
writeStream.flush()

//파일 읽기
val readStream: BufferedReader = file.reader().buffered()
readStream.forEachLine { contents ->
    Log.d(TAG, "File Contents: $contents")
}
```

```
//파일 프로바이더로 외부 공유
//외부에 공유할 경로 설정 res-xml-path
<paths xmlns:android="http://schemas.android.com/apk/res/android">
    <external-path
        name="myfiles"
        path="Android/data/com.example.file/files/Pictures"/>
</paths>

//AndroidManifest
<provider
    android:authorities="com.example.file.fileprovider"
    android:name="androidx.core.content.FileProvider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/path">
    </meta-data>
</provider>


//다른 앱애서 외장 메모리 공간의 앱별 저장소 내 특정 파일을 접근하기 위헤서는  Uri 가 필요함
val file = File.createTempFile(
    "JPEG_${timeStamp}",
    ".jpg",
    storageDir /* /storage/emulated/0/Android/data/com.example.file/files/Pictures */
)

val photoURI: Uri = FileProvider.getUriForFile(
    this,
    /*provider-authorities*/com.example.file.fileprovider,
    file
)
```


//////////


안드로이드 폴더 경로
https://paulaner80.tistory.com/173

안드로이드 저장소 정리
https://juahnpop.tistory.com/218