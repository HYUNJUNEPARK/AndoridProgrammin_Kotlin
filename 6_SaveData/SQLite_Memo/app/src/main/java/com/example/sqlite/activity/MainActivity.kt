package com.example.sqlite.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sqlite.model.Memo
import com.example.sqlite.adapter.RecyclerAdapter
import com.example.sqlite.sqlite.SQLiteHelper
import com.example.sqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val sqliteHelper = SQLiteHelper(this, DATABASE_NAME, 1)
    companion object {
        const val DATABASE_NAME = "memo"
        const val COLUMN_NO = "no"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_DATETIME = "datetime"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initRecycler()
    }

    private fun initRecycler() {
        val adapter = RecyclerAdapter()
        adapter.sqliteHelper = sqliteHelper
        adapter.memoList.addAll(sqliteHelper.selectMemo())
        binding.recyclerMemo.adapter = adapter
        binding.recyclerMemo.layoutManager = LinearLayoutManager(this)

        initSaveButton(adapter)
    }

    private fun initSaveButton(adapter: RecyclerAdapter) {
        binding.buttonSave.setOnClickListener {
            if (binding.editMemo.text.toString().isNotEmpty()) {
                val datetime = System.currentTimeMillis()
                val content = binding.editMemo.text.toString()
                val memo = Memo(null, content, datetime)
                sqliteHelper.insertMemo(memo)
                adapter.memoList.clear()
                adapter.memoList.addAll(sqliteHelper.selectMemo())
                adapter.notifyDataSetChanged()
                binding.editMemo.setText("")
            }
        }
    }
}
