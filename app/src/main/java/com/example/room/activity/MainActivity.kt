package com.example.room.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.room.adapter.MyRecyclerAdapter
import com.example.room.databinding.ActivityMainBinding
import com.example.room.room.MyEntity
import com.example.room.room.MyDatabase

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var roomHelper: MyDatabase? = null
    
    companion object {
        const val TAG = "testLog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        roomHelper = Room.databaseBuilder(this, MyDatabase::class.java, "room_table")
            .allowMainThreadQueries()
            .build()
        val adapter = MyRecyclerAdapter()
        adapter.roomHelper = roomHelper
        adapter.memoList.addAll(roomHelper?.myDao()?.getAll()?: listOf())
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(searchResult: String?): Boolean {
                if (searchResult != null) {
                    val content = searchResult
                    val datetime = System.currentTimeMillis()
                    val memo = MyEntity(null , content, datetime)
                    roomHelper?.myDao()?.insert(memo)
                    adapter.memoList.clear()
                    adapter.memoList.addAll(roomHelper?.myDao()?.getAll()?: listOf())
                    adapter.notifyDataSetChanged()
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}
