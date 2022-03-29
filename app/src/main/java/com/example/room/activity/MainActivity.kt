package com.example.room.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.room.adapter.MyRecyclerAdapter
import com.example.room.databinding.ActivityMainBinding
import com.example.room.room.MigrateDatabase
import com.example.room.room.MyEntity
import com.example.room.room.MyRoomHelper

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    var roomHelper: MyRoomHelper? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        roomHelper = Room.databaseBuilder(this, MyRoomHelper::class.java, "room_table")
            .addMigrations(MigrateDatabase.MIGRATE_1_2)
            .allowMainThreadQueries()
            .build()
        val adapter = MyRecyclerAdapter()
        adapter.roomHelper = roomHelper
        adapter.memoList.addAll(roomHelper?.myDao()?.getAll()?: listOf())
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        initSaveButton(adapter)
    }

    private fun initSaveButton(adapter: MyRecyclerAdapter) {
        binding.buttonSave.setOnClickListener {
            val editMemoTextView = binding.editMemo.text
            if (editMemoTextView.isNotEmpty()) {
                val content = editMemoTextView.toString()
                val datetime = System.currentTimeMillis()
                val memo = MyEntity(null , content, datetime)
                roomHelper?.myDao()?.insert(memo)
                adapter.memoList.clear()
                adapter.memoList.addAll(roomHelper?.myDao()?.getAll()?: listOf())
                adapter.notifyDataSetChanged()
                binding.editMemo.setText("")
            }
        }
    }
}

