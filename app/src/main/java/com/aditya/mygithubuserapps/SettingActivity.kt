package com.aditya.mygithubuserapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Toast
import com.aditya.mygithubuserapps.databinding.ActivitySettingBinding
import com.aditya.mygithubuserapps.notification.AlarmNotification
import com.aditya.mygithubuserapps.preference.SharedPreference

class SettingActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySettingBinding
    private lateinit var sharedPreference: SharedPreference
    private lateinit var alarmNotification: AlarmNotification

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val notifState: Boolean = sharedPreference.getNotifState()
        binding.switchReminder.isChecked = notifState
        binding.switchReminder.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                alarmNotification.setReminder(this, getString(R.string.app_name), getString(R.string.notification_reminder))
                Toast.makeText(this, resources.getString(R.string.notif_on), Toast.LENGTH_SHORT).show()
                sharedPreference.setNotif(true);
            } else {
                alarmNotification.cancelReminder(this);
                Toast.makeText(this, resources.getString(R.string.notif_off), Toast.LENGTH_SHORT)
                    .show()
                sharedPreference.setNotif(false)
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_setting_language -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }
        }
    }
}