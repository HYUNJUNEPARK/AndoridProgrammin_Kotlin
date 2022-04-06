package com.example.sqlite.adapter

import android.graphics.Color
import java.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sqlite.sqlite.SQLiteHelper
import com.example.sqlite.databinding.ItemRecyclerBinding
import com.example.sqlite.model.Memo

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.Holder>() {
    var sqliteHelper: SQLiteHelper? = null
    var memoList = mutableListOf<Memo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }
    inner class Holder(private val binding: ItemRecyclerBinding) : RecyclerView.ViewHolder(binding.root) {
        var mMemo: Memo? = null
        init {
            initDeleteButton()
            initUpdateButton()
        }
        fun setContents(memo: Memo) {
            binding.textNo.text = "${memo.no}"
            binding.textContent.text = memo.content
            val sdf = SimpleDateFormat("yyyy/MM/dd hh:mm")
            binding.textDatetime.text = "${sdf.format(memo.datetime)}"
            this.mMemo = memo
        }
        private fun initDeleteButton() {
            binding.buttonDelete.setOnClickListener {
                sqliteHelper?.deleteMemo(mMemo!!)
                memoList.remove(mMemo)
                notifyDataSetChanged()
            }
        }
        private fun initUpdateButton() {
            binding.buttonUpdate.setOnClickListener {
                if(binding.buttonUpdate.text == "수정"){
                    binding.textEditor.visibility = View.VISIBLE
                    binding.buttonUpdate.text = "저장"
                    binding.buttonUpdate.setTextColor(Color.RED)
                    binding.textEditor.setText("${mMemo?.content}")
                } else {
                    binding.textEditor.visibility = View.INVISIBLE
                    binding.buttonUpdate.text = "수정"
                    binding.buttonUpdate.setTextColor(Color.BLACK)
                    var content = binding.textEditor.text.toString()
                    var dateTime = System.currentTimeMillis()
                    val memo = Memo(mMemo?.no, content, dateTime)
                    sqliteHelper?.updateMemo(memo)
                    memoList.clear()
                    memoList.addAll(sqliteHelper!!.selectMemo())
                    notifyDataSetChanged()
                }
            }
        }
    }
    override fun getItemCount(): Int {
        return memoList.size
    }
    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = memoList[position]
        holder.setContents(memo)
    }
}

