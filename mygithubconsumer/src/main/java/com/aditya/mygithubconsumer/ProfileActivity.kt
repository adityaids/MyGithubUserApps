package com.aditya.mygithubconsumer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProfileActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USER: String = "extra_user"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
    }
}