package com.example.contentresolver_mp3listapp.model

import android.net.Uri
import android.provider.MediaStore

data class Music(var id: String, var title: String?, var artist: String?, var albumId: String?, var duration: Long?) {
    fun getMusicUri(): Uri {
        return Uri.withAppendedPath(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)
    }

    fun getAlbumUri(): Uri {
        return Uri.parse("content://media/external/audio/albumart/$albumId")
    }
}
