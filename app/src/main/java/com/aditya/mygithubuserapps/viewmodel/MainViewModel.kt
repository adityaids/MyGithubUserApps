package com.aditya.mygithubuserapps.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.mygithubuserapps.R
import com.aditya.mygithubuserapps.model.UserDetailModel


class MainViewModel : ViewModel() {

    private val listUser = MutableLiveData<ArrayList<UserDetailModel>>()
    private lateinit var dataName: Array<String>
    private lateinit var dataUserName: Array<String>
    private lateinit var dataAvatar: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>

    fun prepare(context: Context){
        dataName = context.resources.getStringArray(R.array.name)
        dataUserName = context.resources.getStringArray(R.array.username)
        dataAvatar = context.resources.getStringArray(R.array.avatar)
        dataCompany = context.resources.getStringArray(R.array.company)
        dataLocation = context.resources.getStringArray(R.array.location)
        dataRepository = context.resources.getStringArray(R.array.repository)
        dataFollowing = context.resources.getStringArray(R.array.following)
        dataFollower = context.resources.getStringArray(R.array.followers)
        addItem()
    }

    private fun addItem(){
        val listItem = ArrayList<UserDetailModel>()
        for (position in dataName.indices) {
            val userDetailModel = UserDetailModel(
                    dataName[position],
                    dataUserName[position],
                    dataAvatar[position],
                    dataLocation[position],
                    dataCompany[position],
                    dataRepository[position].toInt(),
                    dataFollowing[position].toInt(),
                    dataFollower[position].toInt()
            )
            listItem.add(userDetailModel)
        }
        listUser.postValue(listItem)
    }

    fun getListUser(): LiveData<ArrayList<UserDetailModel>>{
        return listUser
    }
}