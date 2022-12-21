package com.example.networkstate

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.provider.Settings

class MyDialog(private val context: Context) {
    val unConnectionDialog: AlertDialog by lazy {
        AlertDialog.Builder(context)
            .setTitle("네트워크 연결 안 됨")
            .setMessage("WIFE 또는 LTE 연결을 확인해주세요")
            .setPositiveButton("취소") { _, _ -> }
            .setNegativeButton("WIFI 연결") { _, _ ->
                connectWifi()
            }
            .create()
    }
    private fun connectWifi() {
        val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
        context.startActivity(intent)
    }
}