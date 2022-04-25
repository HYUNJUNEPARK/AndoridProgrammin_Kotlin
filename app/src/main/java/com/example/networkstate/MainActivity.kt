package com.example.networkstate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.networkstate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val networkCheck: NetworkConnection by lazy {
        NetworkConnection(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        networkCheck.register()
        initCheckButton()
    }

    override fun onDestroy() {
        super.onDestroy()

        networkCheck.unregister()
    }

    private fun initCheckButton() {
        binding.checkButton.setOnClickListener {
            binding.typeTextView.text = networkCheck.networkType
            binding.stateTextView.text = networkCheck.networkState
        }
    }
}