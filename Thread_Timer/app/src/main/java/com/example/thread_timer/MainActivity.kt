package com.example.thread_timer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import com.example.thread_timer.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private var total = 0
    private var isStarted = false

    private val handler = object: Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            drawTotal()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initButton()
    }

    private fun initButton() {
        binding.buttonStart.setOnClickListener {
            if (!isStarted){
                isStarted = true
                thread(start = true) {
                    while (isStarted) {
                        Thread.sleep(1000)
                        if (isStarted) {
                            total += 1
                            handler?.sendEmptyMessage(0)//핸들러에 메시지를 전달
                        }
                    }
                }
            }
            else {
                isStarted = false
                drawTotal()
            }
        }
        binding.buttonStop.setOnClickListener {
            isStarted = false
            total = 0
            binding.textTimer.text = getString(R.string.init_time)
        }
    }

    private fun drawTotal() {
        val minute = String.format("%02d", total/60)
        val second = String.format("%02d", total%60)
        binding.textTimer.text = "$minute:$second"
    }
}