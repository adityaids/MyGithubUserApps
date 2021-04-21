package com.aditya.mygithubuserapps.adapter

import com.aditya.mygithubuserapps.model.ApiUserModel

interface OnClickedFavoriteItem {
    fun onItemClicked(apiUserModel: ApiUserModel)
}