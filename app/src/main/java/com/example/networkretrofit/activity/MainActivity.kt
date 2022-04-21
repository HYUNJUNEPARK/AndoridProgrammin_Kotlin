package com.example.networkretrofit.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkretrofit.retrofit.RetrofitInterface
import com.example.networkretrofit.adapter.CustomAdapter
import com.example.networkretrofit.companion.Companion.Companion.BASE_URL
import com.example.networkretrofit.databinding.ActivityMainBinding
import com.example.networkretrofit.model.Repository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var retrofit: Retrofit
    private lateinit var retrofitService: RetrofitInterface
    lateinit var adapter: CustomAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
        initRetrofit()
        initRetrofitService()
    }

    private fun initRecyclerView() {
        adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun initRetrofitService() {
        binding.requestButton.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
            Thread {
                retrofitService = retrofit.create(RetrofitInterface::class.java)
                retrofitService.getUsers().enqueue(object : Callback<Repository> {
                    override fun onResponse(call: Call<Repository>, response: Response<Repository>) {
                        adapter.userList = response.body() as Repository
                        runOnUiThread {
                            adapter.notifyDataSetChanged()
                            binding.progressBar.visibility = View.INVISIBLE
                        }
                    }
                    override fun onFailure(call: Call<Repository>, t: Throwable) {
                        runOnUiThread {
                            binding.progressBar.visibility = View.INVISIBLE
                        }
                    }
                })
            }.start()
        }
    }
}
