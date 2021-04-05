package com.aditya.mygithubuserapps.viewmodel

import android.content.Context
import android.content.res.TypedArray
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.mygithubuserapps.R
import com.aditya.mygithubuserapps.api.ApiService
import com.aditya.mygithubuserapps.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainViewModel : ViewModel() {

    companion object {
        const val urlSearch : String = "https://api.github.com/search/"
    }

    private val listUser = MutableLiveData<ArrayList<UserModel>>()
    private val listSearchUser = MutableLiveData<ArrayList<ApiUserModel>>()
    private lateinit var dataName: Array<String>
    private lateinit var dataUserName: Array<String>
    private lateinit var dataAvatar: TypedArray
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>

    fun prepare(context: Context){
        dataName = context.resources.getStringArray(R.array.name)
        dataUserName = context.resources.getStringArray(R.array.username)
        dataAvatar = context.resources.obtainTypedArray(R.array.avatar)
        dataCompany = context.resources.getStringArray(R.array.company)
        dataLocation = context.resources.getStringArray(R.array.location)
        dataRepository = context.resources.getStringArray(R.array.repository)
        dataFollowing = context.resources.getStringArray(R.array.following)
        dataFollower = context.resources.getStringArray(R.array.followers)
        //addItem()
    }

    /*private fun addItem(){
        val listItem = ArrayList<UserModel>()
        for (position in dataName.indices) {
            val userModel = UserModel(
                    dataName[position],
                    dataUserName[position],
                    dataAvatar.getResourceId(position, -1),
                    dataLocation[position],
                    dataCompany[position],
                    dataRepository[position],
                    dataFollowing[position],
                    dataFollower[position]
            )
            listItem.add(userModel)
        }
        listUser.postValue(listItem)
    }*/

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

    fun getListUser(): LiveData<ArrayList<UserModel>>{
        return listUser
    }

    fun getListSearchUser(): LiveData<ArrayList<ApiUserModel>>{
        return listSearchUser
    }
}