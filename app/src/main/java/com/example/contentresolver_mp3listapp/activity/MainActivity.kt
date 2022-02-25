package com.example.contentresolver_mp3listapp.activity

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.contentresolver_mp3listapp.R
import com.example.contentresolver_mp3listapp.adapter.RecyclerAdapter
import com.example.contentresolver_mp3listapp.databinding.ActivityMainBinding
import com.example.contentresolver_mp3listapp.model.Music
import com.example.contentresolver_mp3listapp.permission.Permission

/*
실행 되는지 확인 안되면 - data class Music 초기화 부분 살려보기
data class Music -  Uri 부분 따로 빼기
*/

class MainActivity : Permission() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        requirePermissions(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 999)
    }

    override fun permissionGranted(requestCode: Int) {
        setRecyclerView()
    }

    override fun permissionDenied(requestCode: Int) {
        Toast.makeText(this, R.string.deny_message, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun setRecyclerView() {
        val adapter = RecyclerAdapter()
        adapter.musicList.addAll(getMusicList())
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun getMusicList(): MutableList<Music> {
        val musicList = mutableListOf<Music>()
        val urlList: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val musicInfoArray = arrayOf(
            MediaStore.Audio.Media._ID, MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.ARTIST,
            MediaStore.Audio.Media.ALBUM_ID, MediaStore.Audio.Media.DURATION
        )
        val cursor = contentResolver.query(/*데이터를 가져올 url*/urlList, /*필요한 데이터*/musicInfoArray,null, null, null)
        while (cursor?.moveToNext() == true) {
            val id = cursor.getString(0)
            val title = cursor.getString(1)
            val artist = cursor.getString(2)
            val albumId = cursor.getString(3)
            val duration = cursor.getLong(4)
            val music = Music(id, title, artist, albumId, duration)
            musicList.add(music)
        }
        return musicList
    }
}