package com.june.permission

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
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

        //result : true(0) 모든 권한 승인 / false(-1) 권한 중 하나라도 거절
        if (grantResults.all { result -> result == PackageManager.PERMISSION_GRANTED}) //권한을 모두 승인 받았을 때
        {
            Permission(this).permissionGranted()
        }
        else { //권한 승인이 하나라도 거절되었을 때 -> AlertDialog
            Permission(this).permissionDenied()
        }
    }

    fun authCheckButtonClicked(v: View) {
        Permission(this).checkPermissions()
    }

    fun settingButtonClicked(v: View) {
        Permission(this).applicationInfo()
    }
}