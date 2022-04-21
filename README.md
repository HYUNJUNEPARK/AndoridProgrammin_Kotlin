# Retrofit

레트로핏 통신 라이브러리
`implementation 'com.squareup.retrofit2:retrofit:2.9.0'`

-HttpURLConnection 클래스보다 더 간단하게 HTTP 로 데이터를 통신하는 라이브러리
-레트로핏 통신 라이브러리는 레트로핏 인터페이스를 해석해 HTTP 통신 처리
-모델 클래스에서 키와 프로퍼티 이름이 다를 때 `@SerializedName` 이라는 애너테이션으로 명시
(1) 통신용 함수를 선안한 인토페이스를 장성
(2) Retrofit 에 인터페이스를 전달
(3) Retrofit 이 통신용 서비스 객체를 반환
(4) 서비스의 통신용 함수를 호출한 후에 Call 객체를 반환
(5) Call 객체의 enqueue() 함수를 호출하여 네트워크 통신을 수행

서비스 인터페이스
-네트워크 통신이 필요한 순간에 호출할 함수를 포함하는 인터페이스
-서비스 인터페이스를 통해 Retrofit 이 통신하는 클래스를 자동으로 만들어 주며 이때 함수에 선언한 애너테이션을 보고 그 정보대로 네트워크 통신을 할 수 있는 코드를 자동으로 생성




JSON TO Kotlin Class 플러그인
-JSON 형식으로 된 텍스트 데이터를 코틀린 클래스로 간단하게 변환해주는 플러그인
-[File]-[Settings]->[Plugins] 선택 후 JASON To Kotlin Class 플러그인 검색 후 설치
-기본 패키지 우클릭 -> [New]-[Kotlin data class File from JSON] -> 샘플 JSON 형식 데이터를 복사 붙혀넣고 'Repository' 를 생성 -> 데이터 클래스가 생성됨
(ex. Repository, RepositoryItem, Owner, License)

애너테이션

<<<


GSON 라이브러리
-레트로핏 통신으로 가져온 JSON 데이터를 코틀린 데이터 클래스로 변환해 주는 컨터버
`implementation 'com.squareup.retrofit2:converter-gson:2.9.0'`







JSON TO Kotlin Class 플러그인
-JSON 형식으로 된 텍스트 데이터를 코틀린 클래스로 간단하게 변환해주는 플러그인
-[File]-[Settings]->[Plugins] 선택 후 JASON To Kotlin Class 플러그인 검색 후 설치
-기본 패키지 우클릭 -> [New]-[Kotlin data class File from JSON] -> 샘플 JSON 형식 데이터를 복사 붙혀넣고 'Repository' 를 생성 -> 데이터 클래스가 생성됨
(ex. Repository, RepositoryItem, Owner, License)

MyGlideAPP
-AppGlideModule 를 상속
-@GlideModule 추가

RetrofitInterface
-레트로핏 통신 라이브러리가 RetrofitInterface 를 해석해 HTTP 통신 처리
-호출 방식(ex. @GET), 주소(도메인은 제외하고 작성 ex. users/Kotlin/repos), 데이터(ex. fun users(): Call<Repository>) 등 데이터가 담겨있음



//////
Glide 라이브러리
-https://github.com/bumptech/glide
-URL 주소만 알려주면 해당 이미지가 있는 서버에 접속하여 이미지를 다운로드해서 이미지뷰에 보내는 도구
-AndroidManifest - 인터넷 퍼미션 추가, 앱 수준 build.gradle - dependency / plugins(id 'kotlin-kapt') 추가
-dependencies 에 kapt 설정 추가
-Glide 라이브러리를 사용하기 위해서 MyGlideApp 에 AppGlideModule 를 상속받고 @GlideModule 추가


MyGlideAPP
-AppGlideModule 를 상속
-@GlideModule 추가



//https://api.github.com/users/Kotiln/repos 에 있는 JSON 데이터를 코틀린 데이터 클래스로 변환한 데이터 양식

//val Owner, val License 는 JSON 배열 내 값으로 사용된 JSON 오브젝트로(JSON 오브젝트 안에 JSON 오브젝트)
//Owner 와 License JSON 오브젝트 안에 JSON 데이터(key : value 형식) 중 세부적인 key 값이 명시된 Owner 와 License 데이터 클래스를 패키지에 따로 생성함





