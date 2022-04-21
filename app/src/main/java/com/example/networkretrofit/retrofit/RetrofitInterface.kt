package com.example.networkretrofit.retrofit

import com.example.networkretrofit.companion.Companion.Companion.DETAIL_URL
import com.example.networkretrofit.model.Repository
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {
    @GET(DETAIL_URL)
    fun getUsers(): Call<Repository>
}