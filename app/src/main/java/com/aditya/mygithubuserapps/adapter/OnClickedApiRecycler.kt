package com.aditya.mygithubuserapps.adapter

import com.aditya.mygithubuserapps.model.ApiUserModel

interface OnClickedApiRecycler {
    fun onItemClicked(apiUserModel: ApiUserModel)
}