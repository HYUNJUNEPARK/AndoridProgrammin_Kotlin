# SharedPreferences

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/sharedPreference1.png" height="400"/>
<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/sharedPreference2.png" height="400"/>

---
1. <a href = "#content1">SharedPreferences</a></br>
2. <a href = "#content2">SharedPreferences 객체를 얻는 방법</a></br>
3. <a href = "#content3">데이터 저장/로드</a></br>
4. <a href = "#content4">commit, apply 차이</a></br>
* <a href = "#ref">참고링크</a>
---
><a id = "content1">**1. SharedPreferences**</a></br>


-플랫폼 API 에서 제공하는 클래스</br>
-데이터를 key-value 값을 저장</br>
-간단한 데이터를 저장하는 데 유용하며 내장 메모리의 앱 폴더에 XML 파일로 저장됨</br>
-AndroidX 의 Preference 를 이용하면 앱의 설정 기능을 쉽게 구현할 수 있음</br>
-[res] 우클릭-[New]-[Android Resource File]-> Resource type : XML / Root element : PreferenceScreen / File name : preferences</br>
`implementation 'androidx.preference:preference-ktx:1.1.1'`

**SharedPreferences 의 일반적인 사용 사례**</br>
-사용자 애플리케이션의 첫 방문 체크 (또는 애플리케이션 실행 횟수 카운트)</br>
첫 번째 방문시 지시 사항이나 튜토리얼 또는 스플래시 스크린 액티비티를 보여줄 수 있다</br>
-애플리케이션의 마지막 업데이트 시점 확인</br>
마지막 업데이트 시간을 저장하여 업데이트나 데이터 동기화가 필요한지를 판단할 수 있다</br>
-사용자의 로그인 사용자 이름 저장</br>
-애플리케이션의 상태 저장</br>
-사용자 위치 캐시</br>

앱 설정 UI 샘플 preferences.xml 에서 사용하는 입력필드 종류</br>
a. CheckBoxPreference : 체크박스 타입 입력 필드</br>
b. SwitchPreference : 스위치(ON/OFF) 타입 입력 필드</br>
c. EditTextPreference : 값을 입력하는 타입 입력 필드</br>
d. ListPreference : 목록형 입력 필드 / 띄울 목록과 값은 아래처럼 사용</br>
cf) array.xml : ListPreference 에서 사용할 아이템을 저장해둔 xml 파일</br>

<br></br>
<br></br>

><a id = "content2">**2. SharedPreferences 객체를 얻는 방법**</a></br>

**(1)Activity.getPreferences(int mode)**</br>
-액티비티 단위로 데이터 저장할 때 사용</br>
-함수를 호출한 액티비티 클래스명으로 XML 파일이 자동 생성</br>
-MainActivity 클래스에서 SharedPreferences 객체를 얻었다면 MainActivity.xml 파일이 생성되고 이곳에 데이터가 저장됨</br>
`val sharedPref = getPreferences(Context.MODE_PRIVATE)`</br>

<br></br>

**(2)Context.getShearedPreferences(String name, int mode)**</br>
-앱 전체 데이터를 key-value 형태로 저장하려고 할 때 사용</br>
-첫 번째 매개변수에 지정한 이름의 파일로 데이터가 저장됨</br>
`val sharedPref = getSharedPreference("my_prefs", Context.MODE_PRIVATE)`</br>

<br></br>
<br></br>

><a id = "content3">**3. 데이터 저장/로드**</a></br>

**(1)SharedPreferences.Editor 클래스 함수를 이용해 데이터 저장**</br>
-putBoolean(key, value)</br>
-putInt(key, value)</br>
-putFloat(key, value)</br>
-putLong(key, value)</br>
-putString(key, value)</br>

```
sharedPref.edit().run {
    putString("data1", "aaa")
    putInt("data2", 1)
    commit()
}
```

<br></br>

**(2)SharedPreferences 의 getter 함수로 데이터 로드**</br>
-getBoolean(key, defValue)</br>
-getInt(key, defValue)</br>
-getFloat(key, defValue)</br>
-getLong(key, defValue)</br>
-getString(key, defValue)</br>

```
val data1 = sharedPref.getString("data1", "defValue")
val data2 = sharedPref.getInt("data2", 0)
```

<br></br>
<br></br>

><a id = "content4">**4. commit, apply 차이**</a></br>

-위 메소드들을 이용하여 메모리상 값에 대한 쓰기 작업을 하고 commit 이나 apply 를 호출해야 실제 파일 쓰기 작업을 함</br>

**apply**</br>
-API9 에서 추가된 메소드로 호출 후 바로 리턴되어 스레드를 블록시키지 않음</br>
**commit**</br>
-호출 시 스레드는 block 되고 커널에 파일 저장 완료 후 함수는 리턴되고 스레드는 다시 작동하며 처리결과를 true/false 로 반환함</br>

->**commit : 동기처리**</br>
->**apply : 비동기처리**</br>
**결과 값이 필요 없다면 apply 를 사용하는 게 반응성면에서 좋음!**</br>


<br></br>
<br></br>
---

><a id = "ref">**참고링크**</a></br>

안드로이드 - sharedPreference 란? commit, apply 차이</br>
https://m.blog.naver.com/wonjinho81/220389672520</br>