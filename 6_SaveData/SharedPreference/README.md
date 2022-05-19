# SharedPreferences

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/sharedPreference1.png" height="400"/>
<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/sharedPreference2.png" height="400"/>

---
1. <a href = "#content1">SharedPreferences</a></br>
2. <a href = "#content2">SharedPreferences 객체를 얻는 방법</a></br>
3. <a href = "#content3">데이터 저장/로드</a></br>
* <a href = "#ref">참고링크</a>
---
><a id = "content1">**1. SharedPreferences**</a></br>


-플랫폼 API 에서 제공하는 클래스</br>
-데이터를 key-value 값을 저장</br>
-간단한 데이터를 저장하는 데 유용하며 내장 메모리의 앱 폴더에 XML 파일로 저장됨</br>
-AndroidX 의 Preference 를 이용하면 앱의 설정 기능을 쉽게 구현할 수 있음</br>
-[res] 우클릭-[New]-[Android Resource File]-> Resource type : XML / Root element : PreferenceScreen / File name : preferences</br>

`implementation 'androidx.preference:preference-ktx:1.1.1'`

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
---

><a id = "ref">**참고링크**</a></br>
