package com.aditya.mygithubuserapps.viewmodel

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
        const val urlDetailUser: String = "https://api.github.com/users/"
        const val urlUserFollower: String = "https://api.github.com/users/{username}/followers"
        const val urlUserFollowing: String = "https://api.github.com/users/{username}/following"
    }
    fun getDetailUser(userName: String){
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(urlDetailUser)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiService::class.java)
        val userCall = service.getUserDetail(userName)
        userCall.enqueue(object : Callback<UserDetailModel> {
            override fun onResponse(call: Call<UserDetailModel>, response: Response<UserDetailModel>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<UserDetailModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}