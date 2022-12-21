package com.example.room.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.adapter.MyRecyclerAdapter
import com.example.room.databinding.ActivityMainBinding
import com.example.room.room.DatabaseProvider
import com.example.room.room.MyEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val myDao by lazy { DatabaseProvider.provideDB(applicationContext).myDao()}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = MyRecyclerAdapter()
        adapter.myDao = myDao

        CoroutineScope(Dispatchers.IO).launch {
            adapter.memoList.addAll(myDao.getAll())
        }

        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)
        iniUpdateButton(adapter)
    }

    private fun iniUpdateButton(adapter: MyRecyclerAdapter) {
        binding.updateButton.setOnClickListener {
            val editMemoTextView = binding.editMemo.text
            if (editMemoTextView.isNotEmpty()) {
                val content = editMemoTextView.toString()
                val datetime = System.currentTimeMillis()
                val memo = MyEntity(null, content, datetime)

                //TODO
                CoroutineScope(Dispatchers.IO).launch {
                    myDao.insert(memo)
                }

                adapter.memoList.clear()

                CoroutineScope(Dispatchers.IO).launch {
                    adapter.memoList.addAll(myDao.getAll())
                }

                adapter.notifyDataSetChanged()
                binding.editMemo.setText("")
            }
        }
    }
}

