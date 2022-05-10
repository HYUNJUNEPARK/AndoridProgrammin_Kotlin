package com.example.backgroundservice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.backgroundservice.service.BoundService
import com.example.backgroundservice.service.StartedService

class MainActivity : AppCompatActivity() {
    private val boundService = BoundService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startStartedService(v: View) {
        val intent = Intent(this, StartedService::class.java)
        intent.action = StartedService.ACTION_START
        startService(intent)
    }

    fun stopStartedService(v: View) {
        val intent = Intent(this, StartedService::class.java)
        stopService(intent)
    }

    fun startBindService(v: View) {
        val intent = Intent(this, BoundService::class.java)
        bindService(intent, boundService.boundServiceConnection, Context.BIND_AUTO_CREATE)
    }

    fun stopBindService(v: View) {
        if (boundService.isService) {
            unbindService(boundService.boundServiceConnection)
            boundService.isService = false
        }
    }

    fun callServiceMessage(v: View) {
        if (boundService.isService) {
            val message = boundService.myBoundService?.serviceMessage()
            Toast.makeText(this, "[ message ] : $message", Toast.LENGTH_SHORT).show()
        }
        else {
            Toast.makeText(this, "서비스가 연결되지 않았습니다.", Toast.LENGTH_SHORT).show()
        }
    }
}