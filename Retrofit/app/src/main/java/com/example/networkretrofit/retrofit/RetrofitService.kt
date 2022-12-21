package com.example.networkretrofit.retrofit


import com.example.networkretrofit.model.Repository
import com.example.networkretrofit.retrofit.Url.DETAIL_URL
import retrofit2.Call
import retrofit2.http.GET

/**
 * 서비스 인터페이스
 *
 *
 * 네트워크 통신이 필요한 순간에 호출할 함수를 포함하는 인터페이스
 * 인터페이스를 해석해 레트로핏이 통신하는 클래스를 자동으로 만들어주며 어노테이션(@GET)을 보고 통신할 수 있는 코드 생성
 * 호출 방식(@GET), 세부 주소(DETAIL_URL), 함수(fun gitUsers(): Call<Repository>)가 담겨 있음
 */
interface RetrofitService {
    @GET(DETAIL_URL)
    fun gitUsers(): Call<Repository>
}