package com.example.backgroundservice.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

class StartedService : Service() {
    companion object {
        val ACTION_START = "com.example.backgroundservice.START"
        val ACTION_RUN = "com.example.backgroundservice.RUN"
        val ACTION_STOP = "com.example.backgroundservice.STOP"
    }

    override fun onCreate() {
        super.onCreate()
    }

    override fun onBind(p0: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val action = intent?.action
        Log.d("Service", "[StartedService : action] : $action")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        Log.d("Service", "스타티드 서비스가 종료되었습니다.")
        super.onDestroy()
    }
}