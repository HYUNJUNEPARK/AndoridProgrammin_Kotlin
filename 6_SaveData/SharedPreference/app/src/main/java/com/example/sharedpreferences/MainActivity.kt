package com.example.sharedpreferences

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private lateinit var sharedPreferenceManager: SharedPreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        sharedPreferenceManager = SharedPreferenceManager.getInstance(this@MainActivity)!!
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

    private fun initViews()=with(binding) {
        try {
            sharedPreferenceManager.apply {
                idTextView.text = getDefaultIdString()
                colorBoxView.setBackgroundColor(Color.parseColor(getDefaultColorString()))
                if (getDefaultSwitchBoolean()) {
                    alarmTextView.text = "ON"
                    alarmTextView.setTextColor(getColor(R.color.red))
                }
                else {
                    alarmTextView.text = "OFF"
                    alarmTextView.setTextColor(getColor(R.color.black))
                }
            }
        }
        catch (e: Exception) {
            Toast.makeText(this@MainActivity, "초기화 실패", Toast.LENGTH_SHORT).show()
        }
    }

    fun initializeButtonClicked(v: View) {
        sharedPreferenceManager.initializePrefs()
        initViews()
    }
}