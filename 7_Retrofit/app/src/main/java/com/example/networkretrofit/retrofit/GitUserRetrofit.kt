package com.example.networkretrofit.retrofit

import com.example.networkretrofit.adapter.CustomAdapter
import com.example.networkretrofit.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.coroutines.CoroutineContext

class GitUserRetrofit : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend fun retrofitCreate(adapter: CustomAdapter) = withContext(coroutineContext) {
        val retrofit = retrofit()

        val retrofitInterface = retrofit.create(RetrofitService::class.java)
        retrofitInterface.gitUsers().enqueue(object : Callback<Repository> {
            override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                adapter.userList = response.body() as Repository
                adapter.notifyDataSetChanged()
            }
            override fun onFailure(call: Call<Repository>, t: Throwable) { }
        })
    }

    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

