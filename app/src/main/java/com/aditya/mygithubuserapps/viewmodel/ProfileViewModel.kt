package com.aditya.mygithubuserapps.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.aditya.mygithubuserapps.api.ApiService
import com.aditya.mygithubuserapps.model.UserDetailModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfileViewModel : ViewModel() {
    companion object{
        const val urlUserFollower: String = "https://api.github.com/users/{username}/followers"
        const val urlUserFollowing: String = "https://api.github.com/users/{username}/following"
    }


}