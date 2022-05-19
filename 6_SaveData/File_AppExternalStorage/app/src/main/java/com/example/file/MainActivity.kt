package com.example.file

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.file.databinding.ActivityMainBinding
import java.io.File
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    companion object {
        const val TAG = "testLog"
        const val DETAIL_PICTURES_DIRECTORY = "/Android/data/com.example.file/files/Pictures"
        const val PROVIDER_AUTHORITY = "com.example.file.fileprovider"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            binding.CreateButton.setOnClickListener {
                createFile()
            }
            binding.LoadButton.setOnClickListener {
                loadFileList()
            }
        }
        else {
            Toast.makeText(this, "오류 발생", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createFile() {
        val timeStamp: String = SimpleDateFormat("dd_HH:mm:ss").format(Date())
        val storageDir: File ? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        /* /storage/emulated/0/Android/data/com.example.file/files/Pictures */
        try {
            val file = File.createTempFile(
                "JPEG_${timeStamp}",
                ".jpg",
                storageDir
            )
            openCamera(file)
            Toast.makeText(this, "${file.name} \n 파일 생성", Toast.LENGTH_SHORT).show()
        }
        catch (e: Exception) {
            Toast.makeText(this, "오류 발생", Toast.LENGTH_SHORT).show()
            Log.e(TAG, "Exception: $e")
        }
    }

    private fun loadFileList() {
        var fileName = ""
        val directory_str = Environment.getExternalStorageDirectory().absolutePath + DETAIL_PICTURES_DIRECTORY //storage/emulated/0/Android/data/com.example.file/files/Pictures
        val directory_file: File = File(directory_str) //storage/emulated/0/Android/data/com.example.file/files/Pictures
        val fileList = directory_file.listFiles()
        for(file in fileList) {
            fileName += file.name +"\n"
        }
        binding.fileNameTextView.text = fileName
    }

    //카메라앱(다른앱)에 파일 uri 를 intent 에 넣어 보냈기 때문에 결과적으로는 다른 앱에서 외부 메모리에 있는 앱별 저장소 파일에 접근한 것과 같음
    private fun openCamera(file: File) {
        val photoURI: Uri = FileProvider.getUriForFile(
            this,
            PROVIDER_AUTHORITY,
            file
        )
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
        startActivity(intent)
    }
}