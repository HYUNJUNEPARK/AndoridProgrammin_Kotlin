package com.example.sharedpreferences.setting

import android.os.Bundle
import android.text.TextUtils
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.sharedpreferences.R
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_NAME_COLOR
import com.example.sharedpreferences.sharedpreference.Constants.PREFERENCE_NAME_ID

class SettingFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)

        initIdPreference()
        initColorPreference()
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