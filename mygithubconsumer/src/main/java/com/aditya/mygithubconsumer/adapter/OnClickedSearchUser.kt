package com.aditya.mygithubconsumer.adapter

import com.aditya.mygithubconsumer.model.ApiUserModel

interface OnClickedSearchUser {
    fun onItemClicked(apiUserModel: ApiUserModel)
}