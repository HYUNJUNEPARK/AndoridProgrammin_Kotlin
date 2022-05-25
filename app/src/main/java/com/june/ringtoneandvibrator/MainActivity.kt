package com.june.ringtoneandvibrator

import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val musicUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun ringtoneButtonClicked(v: View) {
        val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val ringtone = RingtoneManager.getRingtone(applicationContext, notification)
        ringtone.play()
    }

    fun vibrationButtonClicked1(v: View) {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(
                50,
                VibrationEffect.DEFAULT_AMPLITUDE)
            )
        }
        else {
            vibrator.vibrate(500)
        }
    }

    fun vibrationButtonClicked2(v: View) {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createWaveform(
                longArrayOf(500, 1000, 500, 2000),
                intArrayOf(0, 50, 0, 200), -1
                )
            )
        }
        else {
            vibrator.vibrate(longArrayOf(500, 1000, 500, 2000), -1)
        }
    }



    fun mediaPlayerButtonClicked(v: View) {
        if (musicUri != null) {
            val player: MediaPlayer = MediaPlayer.create(this, musicUri)
            player.start()
        }
        else {
            Toast.makeText(this, "음원 파일 없음", Toast.LENGTH_SHORT).show()
        }
    }
}