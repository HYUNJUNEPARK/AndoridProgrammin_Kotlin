package com.example.networkstate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.networkstate.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}
    private val networkCheck: NetworkConnectionCallback by lazy { NetworkConnectionCallback(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        networkCheck.register()
    }

    override fun onDestroy() {
        super.onDestroy()

        networkCheck.unregister()
    }

    fun networkStateCheckButtonClicked(v: View) {
        binding.typeTextView.text = networkCheck.networkType
        binding.stateTextView.text = networkCheck.networkState
    }
}