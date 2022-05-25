package com.june.permission

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.june.permission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        binding.requestAuthCountTextView.text = permissions.size.toString()

        //result : 0 모든 권한 승인 / -1 권한 중 하나라도 거절
        if (grantResults.all { result -> result == PackageManager.PERMISSION_GRANTED})
        {
            //권한을 모두 승인 받았을 때
            Toast.makeText(this, "모든 권한 승인", Toast.LENGTH_SHORT).show()
        }
        else {
            //권한 승인이 하나라도 거절되었을 때
            Toast.makeText(this, "권한 거절", Toast.LENGTH_SHORT).show()
        }
    }
    
    fun authCheckButtonClicked(v: View) {
        Permission(this).checkPermissions()
    }
}