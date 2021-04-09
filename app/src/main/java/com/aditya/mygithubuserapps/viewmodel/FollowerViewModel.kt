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

class FollowerViewModel: ViewModel() {

    companion object{
        const val urlUserFollower: String = "https://api.github.com/users/"
    }

    private val listFollower = MutableLiveData<ArrayList<ApiUserModel>>()

    fun getFollowerList(): LiveData<ArrayList<ApiUserModel>> {
        return listFollower
    }

    fun getFollower(userName: String){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(urlUserFollower)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val userListCall = service.getFollower(userName)
        userListCall.enqueue(object : Callback<SearchUserModel?> {
            override fun onResponse(call: Call<SearchUserModel?>, response: Response<SearchUserModel?>) {
                val responseList = ArrayList<ApiUserModel>()
                response.body()?.items?.let { responseList.addAll(it) }
                listFollower.postValue(responseList)
                Log.d("reqAPIFolower", response.body().toString())
            }

            override fun onFailure(call: Call<SearchUserModel?>, t: Throwable) {
                Log.d("requestFollower", t.message.toString())
            }
        })
    }
}