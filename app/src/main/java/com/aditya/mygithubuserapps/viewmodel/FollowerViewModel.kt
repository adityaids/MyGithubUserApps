package com.aditya.mygithubuserapps.viewmodel

import android.util.Log
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

class FollowerViewModel: ViewModel() {

    companion object{
        const val baseUrl: String = "https://api.github.com/users/"
    }

    private val listFollower = MutableLiveData<ArrayList<ApiUserModel>>()
    private val userDetail = MutableLiveData<UserDetailModel>()

    fun getFollower(userName: String){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val userListCall = service.getFollower(userName)
        userListCall.enqueue(object : Callback<ArrayList<ApiUserModel>> {
            override fun onResponse(call: Call<ArrayList<ApiUserModel>?>?, response: Response<ArrayList<ApiUserModel>?>?) {
                val responseList = ArrayList<ApiUserModel>()
                if (response != null) {
                    response.body()?.let { responseList.addAll(it) }
                }
                listFollower.postValue(responseList)
                Log.d("reqAPIFolower", response?.body().toString())
            }

            override fun onFailure(call: Call<ArrayList<ApiUserModel>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }

    fun getDetailUser(userName: String, isFollow: Boolean) {
        var userDetailModel: UserDetailModel
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiService::class.java)
        val userCall = service.getUserDetail(userName)
        userCall.enqueue(object : Callback<UserDetailModel> {
            override fun onResponse(call: Call<UserDetailModel>, response: Response<UserDetailModel>) {
                userDetailModel = UserDetailModel(
                        response.body()?.login,
                        response.body()?.name,
                        response.body()?.avatarUrl,
                        response.body()?.location,
                        response.body()?.company,
                        response.body()?.publicRepos,
                        response.body()?.followers,
                        response.body()?.following,
                        isFollow
                )
                userDetail.postValue(userDetailModel)
            }

            override fun onFailure(call: Call<UserDetailModel>, t: Throwable) {
                Log.d("request detail error", t.message.toString())
            }
        })
    }

    fun getFollowerList(): LiveData<ArrayList<ApiUserModel>> {
        return listFollower
    }
    fun getUserDetail(): LiveData<UserDetailModel>{
        return userDetail
    }
}