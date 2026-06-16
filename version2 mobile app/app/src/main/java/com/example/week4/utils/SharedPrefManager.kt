package com.example.week4.utils

import android.content.Context
import android.content.SharedPreferences

class SharedPrefManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("SophiaPrefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_USERNAME = "username"
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_THEME_DARK = "theme_dark"
    }

    fun saveLoginStatus(isLoggedIn: Boolean, username: String) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn)
        editor.putString(KEY_USERNAME, username)
        editor.apply()
    }

    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }

    fun getUsername(): String? {
        return sharedPreferences.getString(KEY_USERNAME, "")
    }

    fun logout() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun setThemePreference(isDark: Boolean) {
        val editor = sharedPreferences.edit()
        editor.putBoolean(KEY_THEME_DARK, isDark)
        editor.apply()
    }

    fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(KEY_THEME_DARK, false)
    }
}
