package com.example.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class SharedPreferenceManager() {
    companion object {
        //Sync key names in settings.xml
        const val PREFERENCE_NAME_ID = "sp_key_id"
        const val PREFERENCE_NAME_SWITCH = "sp_key_notification_state"
        const val PREFERENCE_NAME_COLOR = "sp_key_color"

        //Default value
        const val PREFERENCE_ID_DEFAULT = "NULL"
        const val PREFERENCE_SWITCH_DEFAULT = false
        const val PREFERENCE_COLOR_DEFAULT = "#FF000000"

        //Singleton
        private var instance: SharedPreferenceManager? = null
        private lateinit var context: Context
        private lateinit var prefs: SharedPreferences
        private lateinit var prefsEditor: SharedPreferences.Editor

        fun getInstance(_context: Context): SharedPreferenceManager? {
            if (instance == null) {
                context = _context
                instance = SharedPreferenceManager()
            }
            return instance
        }
    }

    init {
        prefs = PreferenceManager.getDefaultSharedPreferences(context)
        prefsEditor = prefs.edit()
    }

    fun getDefaultIdString() = prefs.getString(PREFERENCE_NAME_ID, PREFERENCE_ID_DEFAULT)

    fun getDefaultColorString() = prefs.getString(PREFERENCE_NAME_COLOR, PREFERENCE_COLOR_DEFAULT)

    fun getDefaultSwitchBoolean() = prefs.getBoolean(PREFERENCE_NAME_SWITCH, PREFERENCE_SWITCH_DEFAULT)

    fun initializePrefs() {
        prefsEditor.run {
            putString(PREFERENCE_NAME_ID, PREFERENCE_ID_DEFAULT)
            putString(PREFERENCE_NAME_COLOR, PREFERENCE_COLOR_DEFAULT)
            putBoolean(PREFERENCE_NAME_SWITCH, PREFERENCE_SWITCH_DEFAULT)
            apply()
        }
    }
}