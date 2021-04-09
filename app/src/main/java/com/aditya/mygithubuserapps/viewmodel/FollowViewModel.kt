package com.aditya.mygithubuserapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.mygithubuserapps.model.ApiUserModel

class FollowViewModel: ViewModel() {

    companion object{
        const val urlUserFollower: String = "https://api.github.com/users/{username}/followers"
        const val urlUserFollowing: String = "https://api.github.com/users/{username}/following"
    }

    private val listFollower = MutableLiveData<ArrayList<ApiUserModel>>()
    private val listFollowing = MutableLiveData<ArrayList<ApiUserModel>>()

    fun getFollowerList(): LiveData<ArrayList<ApiUserModel>>{
        return listFollower
    }

    fun getFollowingList(): LiveData<ArrayList<ApiUserModel>>{
        return listFollowing
    }

    fun getFollower(userName: String){

    }

    fun getFollowing(userName: String){

    }
}