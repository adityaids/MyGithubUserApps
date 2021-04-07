package com.aditya.mygithubuserapps.adapter

import android.view.View
import com.aditya.mygithubuserapps.model.ApiUserModel

interface OnClickedApiRecycler {
    fun onItemClicked(apiUserModel: ApiUserModel, imageView: View)
}