package com.example.backgroundservice

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import com.example.backgroundservice.databinding.ActivityMainBinding
import com.example.backgroundservice.service.BoundService
import com.example.backgroundservice.service.StartedService

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    //variation for boundService
    var myBoundService:BoundService? = null
    var isService = false
    val boundServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            val binder = service as BoundService.MyBinder
            myBoundService = binder.getService()
            isService = true
            Log.d("Service","BoundService : 연결되었습니다.")
        }
        override fun onServiceDisconnected(name: ComponentName) {
            isService = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initStartedServiceButton()
        initBoundServiceButton()
    }

    private fun initStartedServiceButton() {
        binding.startButton.setOnClickListener {
            val intent = Intent(this, StartedService::class.java)
            intent.action = StartedService.ACTION_START
            startService(intent)
        }

        binding.stopButton.setOnClickListener {
            val intent = Intent(this, StartedService::class.java)
            stopService(intent)
        }
    }

    private fun initBoundServiceButton() {
        binding.bindButton.setOnClickListener {
            val intent = Intent(this, BoundService::class.java)
            bindService(intent, boundServiceConnection, Context.BIND_AUTO_CREATE)
        }
        binding.unbindButton.setOnClickListener {
            if (isService) {
                unbindService(boundServiceConnection)
                isService = false
            }
        }
        binding.callServiceFunButton.setOnClickListener {
            if (isService) {
                val message = myBoundService?.serviceMessage()
                Toast.makeText(this, "[ message ] : $message", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "서비스가 연결되지 않았습니다.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}