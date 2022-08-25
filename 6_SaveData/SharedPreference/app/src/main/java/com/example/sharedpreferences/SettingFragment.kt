package com.example.sharedpreferences

import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.preference.EditTextPreference
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.example.sharedpreferences.SharedPreferenceManager.Companion.PREFERENCE_NAME_COLOR
import com.example.sharedpreferences.SharedPreferenceManager.Companion.PREFERENCE_NAME_ID

class SettingFragment: PreferenceFragmentCompat(), SharedPreferences.OnSharedPreferenceChangeListener {
    private lateinit var sharedPreferenceManager: SharedPreferenceManager
    private lateinit var sharedPreference: SharedPreferences

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings, rootKey)
        try {
            sharedPreferenceManager = SharedPreferenceManager.getInstance(requireContext())!!
            sharedPreference = preferenceScreen.sharedPreferences
            initSummaryProvider()
        }
        catch (e: Exception) {
            Toast.makeText(requireContext(), "초기화 실패", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * SharedPreference 의 Value 가 변하면 호출되는 오버라이드 메서드
     *
     * @param pref 기본 SharedPreferences (PreferenceManager.getDefaultSharedPreferences(context))
     * ex. android.app.SharedPreferencesImpl@24dc5b3
     * @param prefKey : settings.xml 에서 Preference key
     */
    override fun onSharedPreferenceChanged(pref: SharedPreferences?, prefKey: String?) {
        Toast.makeText(requireContext(), "pref : $pref \nkey : $prefKey", Toast.LENGTH_SHORT).show()
    }

    //SharedPreferenceChangeListener 등록
    override fun onResume() {
        super.onResume()
        sharedPreference.registerOnSharedPreferenceChangeListener(this);
    }

    //SharedPreferenceChangeListener 해제
    override fun onPause() {
        super.onPause()
        sharedPreference.unregisterOnSharedPreferenceChangeListener(this)
    }

    /**
     * SummaryProvider 초기화
     *
     * SummaryProvider : Interface definition for a callback to be invoked when the summary of this Preference is requested
     * typically when this preference is added to the hierarchy or its value is updated
     */
    private fun initSummaryProvider() {
        //EditTextPreference
        findPreference<EditTextPreference?>(PREFERENCE_NAME_ID).let { editTextPreference ->
            editTextPreference?.summaryProvider = Preference.SummaryProvider<EditTextPreference>{ preference ->
                val id = preference.text
                if (TextUtils.isEmpty(id)){
                    "설정이 되지 않았습니다."
                }
                else {
                    "설정 ID : $id"
                }
            }
        }
        //ListPreference
        findPreference<ListPreference?>(PREFERENCE_NAME_COLOR).let { listPreference ->
            listPreference?.summaryProvider = ListPreference.SimpleSummaryProvider.getInstance()
        }
    }
}