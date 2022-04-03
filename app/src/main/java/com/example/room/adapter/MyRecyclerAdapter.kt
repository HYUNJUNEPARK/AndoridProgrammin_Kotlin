package com.example.room.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.room.databinding.ItemRecyclerBinding
import com.example.room.room.MyEntity
import com.example.room.room.MyDatabase
import java.text.SimpleDateFormat

class MyRecyclerAdapter: RecyclerView.Adapter<MyRecyclerAdapter.MyHolder>() {
    var memoList = mutableListOf<MyEntity>()
    var roomHelper: MyDatabase?= null //initialized MainActivity onCreate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyHolder(binding)
    }

    inner class MyHolder(private val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root) {
        lateinit var memo: MyEntity

        init {
            binding.buttonDelete.setOnClickListener {
                roomHelper?.myDao()?.delete(memo)
                memoList.remove(memo)
                notifyDataSetChanged()
            }
        }

        fun setContents(memo: MyEntity) {
            this.memo = memo
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("MM/dd")
            binding.dateTextView.text = "${sdf.format(memo.datetime)}"
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