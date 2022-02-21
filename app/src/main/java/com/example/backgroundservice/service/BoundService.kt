package com.example.backgroundservice.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class BoundService : Service() {
    inner class MyBinder : Binder() {
        fun getService() : BoundService {
            return this@BoundService
        }
    }
    private val binder = MyBinder()

    override fun onDestroy() {
        Log.d("Service", "바인드 서비스가 종료되었습니다.")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    fun serviceMessage() : String{
        return "Called BoundService Message"
    }
}