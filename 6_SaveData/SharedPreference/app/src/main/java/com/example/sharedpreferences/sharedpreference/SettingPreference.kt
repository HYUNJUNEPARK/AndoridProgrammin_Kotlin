package com.example.sharedpreferences.sharedpreference

import android.content.Context
import androidx.preference.PreferenceManager
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_COLOR_DEFAULT
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_ID_DEFAULT
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_NAME_COLOR
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_NAME_ID
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_NAME_SWITCH
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_SWITCH_DEFAULT

class SettingPreference(context: Context) {
    private val sharedPref = PreferenceManager.getDefaultSharedPreferences(context)

    fun preferenceId() = sharedPref.getString(PREFERENCE_NAME_ID, PREFERENCE_ID_DEFAULT)
    fun preferenceColor() = sharedPref.getString(PREFERENCE_NAME_COLOR, PREFERENCE_COLOR_DEFAULT)
    fun preferenceSwitchValue() = sharedPref.getBoolean(PREFERENCE_NAME_SWITCH, PREFERENCE_SWITCH_DEFAULT)

    fun initializePref() {
        sharedPref.edit().run {
            putString(PREFERENCE_NAME_ID, PREFERENCE_ID_DEFAULT)
            putString(PREFERENCE_NAME_COLOR, PREFERENCE_COLOR_DEFAULT)
            putBoolean(PREFERENCE_NAME_SWITCH, PREFERENCE_SWITCH_DEFAULT)
            commit()
        }
    }
}