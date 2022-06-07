package com.example.networkretrofit.retrofit


import com.example.networkretrofit.retrofit.RetrofitUrl.Companion.DETAIL_URL
import com.example.networkretrofit.model.Repository
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET(DETAIL_URL)
    fun gitUsers(): Call<Repository>
}