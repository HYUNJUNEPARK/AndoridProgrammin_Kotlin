package com.example.networkretrofit.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.networkretrofit.R
import com.example.networkretrofit.companion.Companion.Companion.TAG
import com.example.networkretrofit.model.Repository
import com.example.networkretrofit.model.RepositoryItem

import com.example.networkretrofit.databinding.ItemRecyclerBinding

class CustomAdapter:RecyclerView.Adapter<CustomAdapter.Holder>() {
    var userList : Repository? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return userList?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val user = userList?.get(position)
        holder.setUser(user)
    }

    inner class Holder(val binding: ItemRecyclerBinding): RecyclerView.ViewHolder(binding.root){
        fun setUser(user: RepositoryItem?) {
            user?.let { repositoryItem ->
                binding.textName.text = repositoryItem.name
                binding.textId.text = repositoryItem.node_id

                //val url = repositoryItem.owner.avatar_url
                //Log.d(TAG, "setUser URL: $url")

                Glide.with(binding.imageAvatar)
                    .load(repositoryItem.owner.avatar_url)
                    .error(R.drawable.ic_baseline_cancel_24)
                    .into(binding.imageAvatar)
                Log.d(TAG, "img: ${repositoryItem.owner}")
            }
        }
    }
}

