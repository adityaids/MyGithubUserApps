package com.aditya.mygithubuserapps.preference

import android.content.Context
import android.content.SharedPreferences




class SharedPreference(context: Context) {
    companion object {
        private const val PREFS_NAME = "Pref"
        private const val APP_FIRST_RUN = "app_first_run"
        private const val SETTING_NOTIF = "setting_notif"
        private const val FIRST_FAVORIT = "first_favorit"
    }
    private var prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setFirstRun(input: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(APP_FIRST_RUN, input)
        editor.apply()
    }

    fun setFirstRunFavorit(input: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(FIRST_FAVORIT, input)
        editor.apply()
    }

    fun getFirstRun(): Boolean {
        return prefs.getBoolean(APP_FIRST_RUN, true)
    }

    fun setNotif(input: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(SETTING_NOTIF, input)
        editor.apply()
    }

    fun getNotifState(): Boolean {
        return prefs.getBoolean(SETTING_NOTIF, true)
    }

    fun getFirstFavorit(): Boolean {
        return prefs.getBoolean(FIRST_FAVORIT, true)
    }
}