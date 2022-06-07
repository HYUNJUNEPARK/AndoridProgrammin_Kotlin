package com.example.networkretrofit.activity

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkretrofit.adapter.CustomAdapter
import com.example.networkretrofit.databinding.ActivityMainBinding
import com.example.networkretrofit.retrofit.GitUserRetrofit

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var adapter: CustomAdapter
    companion object {
        lateinit var progressBar: ProgressBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        progressBar = binding.progressBar
        initRecyclerView()
        initRetrofitService()
    }

    private fun initRecyclerView() {
        adapter = CustomAdapter()
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun initRetrofitService() {
        binding.requestButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            val gitUserRetrofit = GitUserRetrofit()
            gitUserRetrofit.retrofitCreate(adapter)
        }
    }
}
