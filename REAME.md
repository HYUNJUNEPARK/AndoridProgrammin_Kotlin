Room
-ORM(Object Relational Mapping) 라이브러리 : 객체(class)와 RDB 를 매핑하고 변환하는 기술
-SQLite 에서는 쿼리문을 알아야했던것과 달리 ORM 라이브러리인 Room 을 사용하면 쿼리를 몰라도 코드만으로 DB 컨트롤 가능
-클래스 파일에 ORM 을 적용하면 자동으로 쿼리로 변환해 테이블을 생성
-어노테이션 프로세싱(Annotation Processing API) :
클래스명, 변수명 위에 '@명령어' 를 사용하는 것으로 명령어가 컴파일 시 코드로 생성되기 때문에 실행 시 발생할 수 있는 성능 문제가 많이 개선됨

```
//build

plugin {
    id 'kotlin-kapt' //어노테이션 프로세싱을 코틀린에서 사용 가능하게 해줌
}


dependencies {
    def roomVersion = "2.4.2"
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
    kapt("androidx.room:room-compiler:$roomVersion")
}

```




**Room 의 구성요소** : Database, Dao, Entity
**(1)Database**
-RoomDatabase() 를 상속받는 추상 클래스로 Dao 를 상속받는 추상 메서드가 내부에 있음
-Entity, Dao 의 정보가 모여 있는 껍데기 클래스로 최종적으로 Room 에 미리 만들어져 있는 코드를 사용할 수 있음
-entities : Room 라이브러리가 사용할 테이블 클래스 목록
`entities = arrayOf(Entity::class)`
`entities = [Entity::class]`
-version : DB 버전
-exportSchema : true -> 스키마 정보를 파일로 출력

```kotlin
@Database(entities = arrayOf(RoomTable::class), version = 1, exportSchema = false)
abstract class RoomHelper : RoomDatabase() {
    abstract  fun roomMemoDao(): RoomDao
}
```

**(2)Dao**
-데이터베이스에 엑세스하는데 사용되는 메소드(select, inset, delete, join 등)를 갖고 있음
-@RoomDao : DAO 라는 것을 명시(Data Access Object)
-@Query : 쿼리를 직접 작성하고 실행
-@Insert, @Delete


```kotlin
@Dao
interface RoomDao {
    @Query("SELECT * FROM room_table")
    fun getAll(): List<RoomTable>

    @Insert(onConflict = REPLACE)
    fun insert(table: RoomTable)

    @Delete
    fun delete(table: RoomTable)
}
```

**(3)Entity **
-DB 테이블의 정보를 담고 있는 클래스
-@Entity(tableName = "") : 해당 어노테이션이 적용된 클래스를 찾아 테이블로 변환
-@ColumnInfo(name = "") : 컬럼에 대한 정보를 담고 있는 변수위에 사용됨
-@Ignore : 해당 변수가 테이블과 관계없는 변수라는 것을 나타냄
-@PrimaryKey(autoGenerate = true)

```kotlin
@Entity(tableName = "room_table")
class RoomTable {
    @PrimaryKey(autoGenerate = true)

    @ColumnInfo
    var no: Long? = null

    @ColumnInfo
    var content: String = ""

    @ColumnInfo(name = "date")
    var datetime: Long = 0

    constructor(content: String, datetime: Long) {
        this.content = content
        this.datetime = datetime
    }
}
```

//////////////////////////

/*Migration 클래스 : DB 수정
앱 업데이트가 DB 테이블을 변경할 때 이미 기기 내 데이터베이스에 있는 사용자 데이터를 유지하는 것이 중요함
Room 라이브러리는 Migration 클래스를 지원해 유저의 데이터를 유지함
See : https://developer.android.com/training/data-storage/room/migrating-db-versions?hl=ko */






////////////////



Room build version
https://developer.android.com/jetpack/androidx/releases/room