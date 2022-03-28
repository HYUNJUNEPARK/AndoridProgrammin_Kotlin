# SQLite

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/SQLite.jpg" height="400"/>

---
1. <a href = "#content1">override</a></br>
2. <a href = "#content2">database</a></br>
* <a href = "#ref">참고링크</a>
---

SQLiteOpenHelper</br>

-DB 를 파일로 생성하고 코틀린 코드에서 사용할 수 있도록 데이터베이스와 연결하는 역할</br>
`val helper = SQLiteHelper(context, "테이블명", 버전정보)`</br>

><a id = "content1">**1. override**</a></br>

override</br>
-onCreate()</br>
-onUpgrade()</br>

**(1)override fun onCreate(database: SQLiteDatabase?)**</br>
-테이블 생성 쿼리를 정의하고 실행할 수 있으며 테이블이 생성되어 있다면 실행되지 않음</br>
-execSQL() : CREATE, ALTER, DROP, INSERT, UPDATE, DELETE 문을 실행하는 함수</br>
```kotlin
val create = "CREATE TABLE memo (" +
    "no INTEGER PRIMARY KEY, " +
    "content TEXT, " +
    "datetime INTEGER" +
    ")"
database?.execSQL(create)
```
-특별한 이유가 없다면 SQLite 에서 INTEGER 로 선언한 컬럼은 데이터 클래스에서 Long 으로 사용함</br>
`data class Memo(var number:Long?, var content:String, var dateTime:Long)`</br>

<br></br>
**(2)override fun onUpgrade(database: SQLiteDatabase?, p1: Int, p2: Int)**</br>
-SQLiteHelper 에 전달되는 버전 정보가 변경되었을 때 현재 생성되어 있는 DB 버전보다 높으면 호출됨</br>
-버전 변경 사항이 없으면 호출되지 않음</br>

<br></br>
<br></br>

><a id = "content2">**2. database**</a></br>

database</br>
-writableDatabase</br>
-readableDatabase</br>

**(1)writableDatabase**</br>
-삽입(쓰기) 전용 DB. 사용 후 close 호출</br>
-ContentValues() 를 인스턴스화하고 put("컬럼명", 값) 메서드로 데이터 저장</br>
```kotlin
val values = ContentValues()
values.put("content", memo.content)
```

-insert("테이블명", null, 저장할 값)</br>
```kotlin
val wd = writableDatabase
wd.insert("memo", null, values)
wd.close()
```

-update("테이블명", 수정할 값, 수정조건(PRIMARY KEY), 3번째 파라미터에 매핑할 값)</br>
```
//update
val wd = writableDatabase
wd.update("memo", values, "no = ${memo.no}", null)
//wd.update("memo", values, "no = ?", arrayOf("${memo.no}"))
wd.close()
```
<br></br>
**(2)readableDatabase**</br>
-읽기(조회) 전용 DB. 사용 후 close 호출</br>
-커서(cursor) : 쿼리를 통해 반환된 데이터셋을 반복문으로 하나씩 처리 할 수 있음</br>
-rawQuery() : 테이블에 저장된 데이터 조회 함수로 SELECT 문을 실행하고 Cursor 객체를 반환</br>

```kotlin
val list = mutableListOf<Memo>()
val readQuery = "select * from memo"
val rd = readableDatabase
val cursor = rd.rawQuery(readQuery, null)

while (cursor.moveToNext()) {
    val no = cursor.getLong(cursor.getColumnIndex("no"))
    val content = cursor.getString(cursor.getColumnIndex("content"))
    val datetime = cursor.getLong(cursor.getColumnIndex("datetime"))
    list.add(Memo(no, content, datetime))
}
cursor.close()
rd.close()
```

<br></br>
<br></br>

---

><a id = "ref">**참고링크**</a></br>

