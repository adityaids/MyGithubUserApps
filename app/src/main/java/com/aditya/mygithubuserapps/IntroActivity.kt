package com.aditya.mygithubuserapps

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.aditya.mygithubuserapps.notification.AlarmNotification
import com.aditya.mygithubuserapps.preference.SharedPreference


class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Handler(Looper.getMainLooper()).postDelayed({
            checkOnFirst()
            startActivity(Intent(this@IntroActivity, MainActivity::class.java))
            finish()
        }, 10000L)
    }
    private fun checkOnFirst() {
        val sharedPreference = SharedPreference(this)
        val firstRun: Boolean = sharedPreference.getFirstRun()
        if (firstRun) {
            val reminderBroadcast = AlarmNotification()
            reminderBroadcast.setReminder(
                this,
                getString(R.string.app_name),
                resources.getString(R.string.notification_reminder)
            )
            sharedPreference.setFirstRun(false)
        }
    }
}