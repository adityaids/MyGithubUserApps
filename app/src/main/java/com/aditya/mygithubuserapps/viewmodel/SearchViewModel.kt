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

class SearchViewModel : ViewModel() {
    companion object {
        const val urlSearch : String = "https://api.github.com/search/"
    }

    private val listSearchUser = MutableLiveData<ArrayList<ApiUserModel>>()

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

    fun getListSearchUser(): LiveData<ArrayList<ApiUserModel>> {
        return listSearchUser
    }
}