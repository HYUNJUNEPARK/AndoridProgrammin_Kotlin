package com.example.room.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemRecyclerBinding
import com.example.room.room.MyDao
import com.example.room.room.MyEntity
import com.example.room.room.MyRoomHelper
import java.text.SimpleDateFormat

class MyRecyclerAdapter: RecyclerView.Adapter<MyRecyclerAdapter.MyHolder>() {
    var memoList = mutableListOf<MyEntity>()
    var myDao: MyDao ?= null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    inner class MyHolder(private val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var memo: MyEntity

        init {
            binding.buttonDelete.setOnClickListener {
                myDao?.delete(memo)
                memoList.remove(memo)
                notifyDataSetChanged()
            }
            binding.buttonUpdate.setOnClickListener {
                if(binding.buttonUpdate.text == "수정"){
                    binding.textEditor.visibility = View.VISIBLE
                    binding.buttonUpdate.text = "저장"
                    binding.buttonUpdate.setTextColor(Color.RED)
                    binding.textEditor.setText("${memo?.content}")
                } else {
                    binding.textEditor.visibility = View.INVISIBLE
                    binding.buttonUpdate.text = "수정"
                    binding.buttonUpdate.setTextColor(Color.WHITE)
                    val content = binding.textEditor.text.toString()
                    val datetime = System.currentTimeMillis()
                    val memo = MyEntity(memo.no, content, datetime)
                    myDao?.update(memo)
                    memoList.clear()
                    memoList.addAll(myDao?.getAll()?: listOf())
                    notifyDataSetChanged()
                }
            }
        }

        fun setContents(memo: MyEntity) {
            this.memo = memo
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
        }
    }

    override fun onBindViewHolder(myHolder: MyHolder, position: Int) {
        val memo = memoList[position]
        myHolder.setContents(memo)
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
}