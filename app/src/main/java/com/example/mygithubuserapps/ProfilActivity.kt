package com.example.mygithubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ProfilActivity : AppCompatActivity() {
    companion object{
        val EXTRA_IMAGE_TRANSITION: String = "extra_image_transition"
        val EXTRA_DATA: String = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
    }
}