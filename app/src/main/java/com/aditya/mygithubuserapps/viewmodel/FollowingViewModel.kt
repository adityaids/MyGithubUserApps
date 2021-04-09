package com.aditya.mygithubuserapps.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.mygithubuserapps.api.ApiService
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.SearchUserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class FollowingViewModel: ViewModel() {
    companion object{
        const val urlUserFollowing: String = "https://api.github.com/users/"
    }

    private val listFollowing = MutableLiveData<ArrayList<ApiUserModel>>()

    fun getFollowing(userName: String){

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(urlUserFollowing)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val userListCall = service.getFollowing(userName)
        userListCall.enqueue(object : Callback<SearchUserModel?> {
            override fun onResponse(call: Call<SearchUserModel?>, response: Response<SearchUserModel?>) {
                val responseList = ArrayList<ApiUserModel>()
                response.body()?.items?.let { responseList.addAll(it) }
                listFollowing.postValue(responseList)
            }

            override fun onFailure(call: Call<SearchUserModel?>, t: Throwable) {
                Log.d("requestFollower", t.message.toString())
            }
        })
    }

    fun getFollowingList(): LiveData<ArrayList<ApiUserModel>> {
        return listFollowing
    }
}