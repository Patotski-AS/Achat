package com.progerchat.achat.screens.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.progerchat.achat.R

class SettingsPreference: PreferenceFragmentCompat(){

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.settings)
    }

}