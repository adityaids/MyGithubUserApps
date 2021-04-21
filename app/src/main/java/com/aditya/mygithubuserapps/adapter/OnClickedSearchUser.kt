package com.aditya.mygithubuserapps.adapter

import com.aditya.mygithubuserapps.model.ApiUserModel

interface OnClickedSearchUser {
    fun onItemClicked(apiUserModel: ApiUserModel)
}