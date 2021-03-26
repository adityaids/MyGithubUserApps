package com.example.mygithubuserapps.viewmodel

import android.content.Context
import android.content.res.TypedArray
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubuserapps.R
import com.example.mygithubuserapps.adapter.UserHorizontalAdapter
import com.example.mygithubuserapps.adapter.UserVerticalAdapter
import com.example.mygithubuserapps.model.UserModel

class MainViewModel : ViewModel() {

    private val listUser = MutableLiveData<ArrayList<UserModel>>()
    private lateinit var dataName: Array<String>
    private lateinit var dataUserName: Array<String>
    private lateinit var dataAvatar: TypedArray
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>

    fun prepare(context : Context){
        dataName = context.resources.getStringArray(R.array.name)
        dataUserName = context.resources.getStringArray(R.array.username)
        dataAvatar = context.resources.obtainTypedArray(R.array.avatar)
        dataCompany = context.resources.getStringArray(R.array.company)
        dataLocation = context.resources.getStringArray(R.array.location)
        dataRepository = context.resources.getStringArray(R.array.repository)
        dataFollowing = context.resources.getStringArray(R.array.following)
        dataFollower = context.resources.getStringArray(R.array.followers)
        addItem()
    }

    private fun addItem(){
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
    }

    fun getListUser(): LiveData<ArrayList<UserModel>>{
        return listUser
    }
}