package com.example.networkretrofit

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.networkretrofit.databinding.ActivityMainBinding
import com.example.networkretrofit.retrofit.GitUserRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var adapter: RecyclerViewAdapter
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() = with(binding) {
        try {
            adapter = RecyclerViewAdapter()
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
        }
        catch (e: Exception) {
            Toast.makeText(this@MainActivity, "리사이클러뷰 초기화 실패", Toast.LENGTH_SHORT).show()
        }
    }

    fun requestButtonClicked(v: View) {
        launch(coroutineContext) {
            showProgress()
            GitUserRetrofit().retrofitCreate(adapter)
            dismissProgress()
        }
    }

    private suspend fun showProgress() = withContext(coroutineContext) {
        binding.progressBar.visibility = View.VISIBLE
    }

    private suspend fun dismissProgress() = withContext(coroutineContext) {
        binding.progressBar.visibility = View.INVISIBLE
    }
}
