package com.example.networkretrofit.retrofit

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.networkretrofit.activity.MainActivity.Companion.progressBar
import com.example.networkretrofit.adapter.CustomAdapter
import com.example.networkretrofit.model.Repository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class GitUserRetrofit {
    private fun retrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(RetrofitUrl.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun retrofitCreate(adapter: CustomAdapter) {
        val retrofit = retrofit()
        CoroutineScope(Dispatchers.IO).launch {
            val retrofitInterface = retrofit.create(RetrofitInterface::class.java)
            retrofitInterface.gitUsers().enqueue(object : Callback<Repository> {
                override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                    adapter.userList = response.body() as Repository
                    Log.d("testLog", "onResponse: ${adapter.userList}")
                    adapter.notifyDataSetChanged()
                    progressBar.visibility = View.INVISIBLE
                }
                override fun onFailure(call: Call<Repository>, t: Throwable) {
                    progressBar.visibility = View.INVISIBLE
                }
            })
        }
    }
}

