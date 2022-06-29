package com.example.sharedpreferences.setting

import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.sharedpreferences.R
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_NAME_COLOR
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_NAME_ID

class SettingFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        initIdPreference()
        initColorPreference()
    }

    override fun onSharedPreferenceChanged(pref: SharedPreferences?, prefKey: String?) {
        /*
        pref : android.app.SharedPreferencesImpl@24dc5b3
        prefKey : noti_message/id/color
        */
        Toast.makeText(requireContext(), "preference key : $prefKey", Toast.LENGTH_SHORT).show()
    }

    override fun onResume() {
        super.onResume()

        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    override fun onPause() {
        super.onPause()

        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun initIdPreference() {
        val idPreference: EditTextPreference? = findPreference(PREFERENCE_NAME_ID)
        idPreference?.summaryProvider =
            Preference.SummaryProvider<EditTextPreference>{ preference ->
                val id = preference.text
                if (TextUtils.isEmpty(id)){
                    "설정이 되지 않았습니다."
                }
                else {
                    "설정 ID : $id"
                }
            }
    }

    private fun initColorPreference() {
        val colorPreference: ListPreference? = findPreference(PREFERENCE_NAME_COLOR)
        colorPreference?.summaryProvider =
            ListPreference.SimpleSummaryProvider.getInstance()
    }
}