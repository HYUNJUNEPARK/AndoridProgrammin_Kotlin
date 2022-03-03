package com.example.coroutines_imgdownloadapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutines_imgdownloadapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}