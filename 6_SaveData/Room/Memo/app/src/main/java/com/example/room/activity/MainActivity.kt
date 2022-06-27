package com.example.room.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.adapter.MyRecyclerAdapter
import com.example.room.databinding.ActivityMainBinding
import com.example.room.room.DatabaseProvider
import com.example.room.room.MyEntity

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

        //TODO
        adapter.memoList.addAll(myDao.getAll())

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
                myDao.insert(memo)

                adapter.memoList.clear()
                adapter.memoList.addAll(myDao.getAll())
                adapter.notifyDataSetChanged()
                binding.editMemo.setText("")
            }
        }
    }
}

