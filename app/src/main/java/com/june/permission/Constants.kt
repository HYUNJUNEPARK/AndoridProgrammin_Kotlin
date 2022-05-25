package com.june.permission

import android.Manifest

class Constants {
    companion object {
        const val permissionRequestCode = 999

        val permissionsArray: Array<String> = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_NUMBERS
        )
    }
}