package com.example.vinoteka.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SessionManager @Inject constructor(@ApplicationContext private val appContext: Context) {

    companion object {
        private const val PREFERENCES_APP = "prefs_app"
        private const val ADMIN_EMAIL = "adminEmail"
    }

    private val appPreferences: SharedPreferences by lazy {
        appContext.getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE)
    }

    var adminEmail: String?
        get() = appPreferences.getString(ADMIN_EMAIL, null)
        set(value) {
            appPreferences.edit().putString(ADMIN_EMAIL, value).apply()
        }
}
