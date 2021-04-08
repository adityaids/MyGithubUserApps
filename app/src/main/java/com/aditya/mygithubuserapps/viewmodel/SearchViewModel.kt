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

class SearchViewModel : ViewModel() {
    companion object {
        const val urlSearch : String = "https://api.github.com/search/"
        const val urlDetailUser: String = "https://api.github.com/users/"
    }

    private val listSearchUser = MutableLiveData<ArrayList<ApiUserModel>>()
    private val userDetail = MutableLiveData<UserDetailModel>()

    fun setQuerySarch(q: String){
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(urlSearch)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val userListCall = service.getUserList(q)
        userListCall.enqueue(object : Callback<SearchUserModel?> {
            override fun onResponse(call: Call<SearchUserModel?>?, response: Response<SearchUserModel?>) {
                if (response.body() != null) {
                    val responseList = ArrayList<ApiUserModel>()
                    response.body()?.items?.let { responseList.addAll(it) }
                    listSearchUser.postValue(responseList)
                }
            }

            override fun onFailure(call: Call<SearchUserModel?>, t: Throwable) {
                Log.d("MainActivity", "error loading from API")
            }
        })
    }

    fun getDetailUser(userName: String) {
        var userDetailModel: UserDetailModel
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(urlDetailUser)
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
                    false
                )
                userDetail.postValue(userDetailModel)
            }

            override fun onFailure(call: Call<UserDetailModel>, t: Throwable) {
                Log.d("request detail error", t.message.toString())
            }
        })
    }

    fun getUserFollower(urlFollowers: String){

    }

    fun getUserFollowing(urlFollowing: String){

    }

    fun getListSearchUser(): LiveData<ArrayList<ApiUserModel>> {
        return listSearchUser
    }
    fun getUserDetail(): LiveData<UserDetailModel>{
        return userDetail
    }
}