MediaStore
-안드로이드는 미디어 정보를 저장하는 저장소 용도로 MediaStore 를 사용함
-각각의 미디어가 종류별로 DB의 테이블처럼 있고, 각 테이블당 주소가 하나씩 제공됨. 테이블 주소와 칼럼명은 상수로 제공
-미디어의 종류마다 1개의 주소를 가진 콘텐트 프로바이더가 구현되어 있음
-외부 저장소에 있기 때문에 외부 저장소를 읽는 권한이 필요

MediaStore 테이블 주소
-이미지: MediaStore.Images.Media.EXTERNAL_CONTENT_URI
-오디오: MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
-비디오: MediaStore.Video.Media.EXTERNAL_CONTENT_URI

콘텐트 프로바이더
-내가 만든 앱의 데이터를 다른 앱에서도 사용할 수 있게 제공할 때 필요한 도구
-안드로이드 4대 메이저 컴포넌트지만 OS 에 이미 구현되어 있어(연락처, 갤러리, 음악 등) 앱 개발을 하면서 사용할 일은 거의 없음

콘텐트 리졸버
-콘텐트 프로바이더로부터 데이터를 가져오는 도구
-query() 메서드 제공
`val cursor = contentResolver.query(listUrl, dataCol, null, null, null)`

```java
public final Cursor query(
@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection,
@Nullable String[] selectionArgs, @Nullable String sortOrder
)
/*
uri : 테이블 주소 Uri
projection : 테이블의 컬럼명 배열
selection : 데이터 검색 조건. 어떤 컬럼을 검색할 것인지 컬럼명 지정 ex) name=?, title=? 형태
selectionArgs : 조건 값. 세 번째 컬럼명에 입력할 값
sortOrder : 정렬 순서 ex) ORDER BY title
*/

```

커서(cursor)
-컨텐트 리졸버가 요청한 쿼리를 통해 반환된 데이터셋을 반복문으로 하나씩 처리 할 수 있음
-`contentResolver.query()` 로 커서를 만들어 콘텐트 프로바이더로 부터 '데이터 읽기 -> 다음줄 이동' 으로 데이터를 가져옴
`val cursor = contentResolver.query(url 주소, 테이블에서 필요한 컬럼, null, null, null)`


콘텐트 리졸버로 데이터를 읽어오는 과정

```kotlin
//0. 권한 명세와 요청
//AndroidManifest
<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
//MainActivity
requirePermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 999)

//1. 데이터 주소(MediaStore 테이블 주소) 정의
val listUrl = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI

//2. 가져올 칼럼명 정의
val dataCol = arrayOf(
    MediaStore.Audio.Media._ID,
    MediaStore.Audio.Media.TITLE,
    MediaStore.Audio.Media.ARTIST,
    MediaStore.Audio.Media.ALBUM_ID,
    MediaStore.Audio.Media.DURATION
)

//3. 데이터 클래스 정의
data class Music (id: String, title: String?, artist: String?, albumId: String?, duration: Long?)

//4. 쿼리 실행
val cursor = contentResolver.query(listUrl, dataCol, null, null, null)
```

////////////////////////

startProcess() : 리사이클러뷰 세팅
getMusicList() : 컨텐트 리졸버가 외부 저장소(MediaStore)에 요청한 데이터를 커서(cursor)로 받아옴
-val listUrl : 음원 데이터 uri 를 담아 둔 변수
-val dataCol : 음원 데이터 컬럼(불러올 데이터 목록)
-val musicList : 커서로 전달받은 데이터를 저장할 변수

MediaPlayer : 음원을 재생하는 클래스
mediaPlayer != null
-item 목록을 클릭할 때마다 음악 중복 실행 방지
-mediaPlayer != null : 이미 mediaPlayer 에 데이터가 있다 == 음악이 재생 중이다
-mediaPlayer = null 으로 재생 중인 음원을 비워주고 클릭 된 Uri 에 해당하는 음원으로 채움


getMusicUri() : 음원의 uri 를 생성하는 메서드
-음원 uri = '기본 MediaStore 의 주소' + '음원 id'

getAlbumUri() : 앨범 아트 uri 를 생성하는 메서드
-The method 'Uri.parse()' creates a new Uri object from a properly formatted String (String -> Uri)

//////////////////////////



