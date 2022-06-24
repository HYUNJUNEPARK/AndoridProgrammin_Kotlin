package com.example.sharedpreferences

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreferences.databinding.ActivityMainBinding
import com.example.sharedpreferences.setting.SettingActivity
import com.example.sharedpreferences.sharedpreference.SettingPreference

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initViews()
    }

    override fun onResume() {
        super.onResume()

        initViews()
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

    private fun initViews() {
        SettingPreference(this).apply {
            binding.idTextView.text = preferenceId()
            binding.colorBoxView.setBackgroundColor(Color.parseColor(preferenceColor()))
            if (preferenceSwitchValue()) {
                binding.alarmTextView.text = "ON"
                binding.alarmTextView.setTextColor(getColor(R.color.red))
            }
            else {
                binding.alarmTextView.text = "OFF"
                binding.alarmTextView.setTextColor(getColor(R.color.black))
            }
        }
    }

    fun initializeButtonClicked(v: View) {
        SettingPreference(this).initializePref()
        initViews()
    }
}