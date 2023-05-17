package com.example.vinoteka.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManager @Inject constructor(@ApplicationContext private val appContext: Context) {

    companion object {
        private const val PREFERENCES_APP = "prefs_app"
        private const val VERIFIED = "verified"
    }

    private val appPreferences: SharedPreferences by lazy {
        appContext.getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE)
    }

    var isVerified: Boolean
        get() = appPreferences.getBoolean(VERIFIED, false)
        set(value) {
            appPreferences.edit().putBoolean(VERIFIED, value).apply()
        }
}
