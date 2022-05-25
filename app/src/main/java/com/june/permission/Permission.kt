package com.june.permission

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.ActivityCompat

class Permission(val context: Context) {
    fun checkPermissions() {
        //AOS M 버전 미만 권한 요청
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {

        }
        //AOS M 버전 이상 권한 요청
        else {
            val isAllPermissionGranted = Constants.permissionsArray.all { permission ->
                context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
            }

            if (isAllPermissionGranted) {
                Toast.makeText(context, "권한 승인", Toast.LENGTH_SHORT).show()
            }
            else {
                ActivityCompat.requestPermissions(
                    context as Activity,
                    Constants.permissionsArray,
                    Constants.permissionRequestCode
                )
            }
        }
   }
   fun permissionGranted() {

   }
   fun permissionDenied() {

   }
}