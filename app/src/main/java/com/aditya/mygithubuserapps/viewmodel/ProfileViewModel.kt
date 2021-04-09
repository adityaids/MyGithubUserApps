package com.aditya.mygithubuserapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.mygithubuserapps.api.ApiService
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.SearchUserModel
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

    private val listFollower = MutableLiveData<ArrayList<ApiUserModel>>()
    private val listFollowing = MutableLiveData<ArrayList<ApiUserModel>>()

    fun getFollowerList(): LiveData<ArrayList<ApiUserModel>> {
        return listFollower
    }

    fun getFollowingList(): LiveData<ArrayList<ApiUserModel>> {
        return listFollowing
    }

    fun getFollower(userName: String){
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(urlUserFollower)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiService::class.java)
        val userListCall = service.getFollower(userName)
        userListCall.enqueue(object : Callback<SearchUserModel?>{
            override fun onResponse(call: Call<SearchUserModel?>, response: Response<SearchUserModel?>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<SearchUserModel?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    fun getFollowing(userName: String){

        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(urlUserFollowing)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiService::class.java)
        val userListCall = service.getFollowing(userName)
        userListCall.enqueue(object : Callback<SearchUserModel?>{
            override fun onResponse(call: Call<SearchUserModel?>, response: Response<SearchUserModel?>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(call: Call<SearchUserModel?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

}