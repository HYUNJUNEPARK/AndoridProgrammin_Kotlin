# ContentResolver_MP3ListApp


---
1. <a href = "#content1">MediaStore</a></br>
2. <a href = "#content2">콘텐트 리졸버</a></br>
3. <a href = "#content3">커서(cursor)</a></br>
* <a href = "#ref">참고링크</a>
---

><a id = "content1">**1. MediaStore**</a></br>

**MediaStore**</br>
-안드로이드는 미디어 정보를 저장하는 저장소 용도로 MediaStore 를 사용함</br>
-각각의 미디어가 종류별로 DB의 테이블처럼 있고, 각 테이블당 주소가 하나씩 제공됨. 테이블 주소와 칼럼명은 상수로 제공</br>
-미디어의 종류마다 1개의 주소를 가진 콘텐트 프로바이더가 구현되어 있음</br>
-외부 저장소에 있기 때문에 외부 저장소를 읽는 권한이 필요</br>

**MediaStore 종류**</br>
-이미지: MediaStore.Images.Media.EXTERNAL_CONTENT_URI</br>
-오디오: MediaStore.Audio.Media.EXTERNAL_CONTENT_URI</br>
-비디오: MediaStore.Video.Media.EXTERNAL_CONTENT_URI</br>

<br></br>
<br></br>

><a id = "content2">**2. 콘텐트 리졸버**</a></br>

**콘텐트 프로바이더**</br>
-내가 만든 앱의 데이터를 다른 앱에서도 사용할 수 있게 제공할 때 필요한 도구</br>
-안드로이드 4대 메이저 컴포넌트지만 OS 에 이미 구현되어 있어(연락처, 갤러리 등) 앱 개발을 하면서 사용할 일은 거의 없음</br>
cf. 안드로이드 4대 메이저 컴포넌트 - 액티비티, 서비스, 콘텐트프로바이더, 브로드캐스트 리시버</br>


**콘텐트 리졸버**</br>
-콘텐트 프로바이더로부터 데이터를 가져오는 도구</br>
-query() 메서드 제공</br>
`val cursor = contentResolver.query(uri, dataCol, null, null, null)`</br>

<br></br>
<br></br>

><a id = "content3">**3. 커서(cursor)**</a></br>

**커서**</br>
-컨텐트 리졸버가 요청한 쿼리 결과가 반환되는 클래스로 반환된 결과를 반복문으로 하나씩 처리</br>
-`val cursor = contentResolver.query()` </br>

```kotlin
private fun getMusicList(): MutableList<Music> {
    val musicList = mutableListOf<Music>()
    val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    val musicInfoArray = arrayOf(
        MediaStore.Audio.Media._ID,
        MediaStore.Audio.Media.TITLE
        /*...추가로 필요한 데이터...*/
    )
    val cursor = contentResolver.query(uri, musicInfoArray, null, null, null)
    while (cursor?.moveToNext() == true) {
        val id = cursor.getString(0)
        val title = cursor.getString(1)
        val music = Music(id, title)
        musicList.add(music)
    }
    return musicList
}
```


**커서 파라미터 정리**

```java
public final Cursor query(
@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
@Nullable String[] selectionArgs, @Nullable String sortOrder
)
```

-uri : 데이터를 가져올 uri 주소</br>
-projection : 테이블의 컬럼명 배열(필요한 데이터)</br>
-selection : 데이터 검색 조건. 어떤 컬럼을 검색할 것인지 컬럼명 지정 ex) name=?, title=? 형태</br>
-selectionArgs : 조건 값. 세 번째 컬럼명에 입력할 값</br>
-sortOrder : 정렬 순서 ex) ORDER BY title</br>

<br></br>


>**정리**
```kotlin
//AndroidManifest
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>

//model
data class Music(var id: String, var title: String?, var artist: String?, var albumId: String?, var duration: Long?)


//contentResolver & cursor
private fun getMusicList(): MutableList<Music> {
    /*
    ...
    데이터를 가져올 url, 필요한 데이터 세팅
    ...
    */
    val cursor = contentResolver.query(/*데이터를 가져올 url*/, /*필요한 데이터*/, null, null, null)

    while (cursor?.moveToNext() == true) {
        /*
        가져온 데이터 초기화(id, title, artist, albumId, duration)
        val id = cursor.getString(0)
        ...
        */
        val music = Music(id, title, artist, albumId, duration)
        musicList.add(music)
    }
    return musicList
}

//반환받은 musicList 를 RecyclerView 에 세팅
```

<br></br>
<br></br>
---

><a id = "ref">**참고링크**</a></br>

Status bar 투명으로 설정</br>
https://jaeryo2357.tistory.com/92</br>

MediaStore file 참고 링크</br>
https://developer.android.com/training/data-storage/shared/media?hl=ko</br>
