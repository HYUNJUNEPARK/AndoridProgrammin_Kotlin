package com.example.sharedpreferences

import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var shared: SharedPreferences

    companion object {
        const val TAG = "testLog"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        shared = PreferenceManager.getDefaultSharedPreferences(this)

        getId()
        getColor()
        getSwitchValue()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.settingMenu -> {
                val intent = Intent(this, SettingActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getId() {
        val sharedId = shared.getString("id", "Null")
        binding.idTextView.text = sharedId
    }

    private fun getColor() {
        val sharedColor = shared.getString("color", "#FF000000")
        binding.colorBoxView.setBackgroundColor(Color.parseColor(sharedColor))
    }
    private fun getSwitchValue() {
        val switchValue = shared.getBoolean("noti_message", false)
        if (switchValue) {
            binding.alarmTextView.text = "ON"
            binding.alarmTextView.setTextColor(getColor(R.color.red))
        }
        else {
            binding.alarmTextView.text = "OFF"
        }
    }
}