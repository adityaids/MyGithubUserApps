package com.aditya.mygithubuserapps.adapter

import android.view.View
import com.aditya.mygithubuserapps.model.UserDetailModel

interface OnClickedRecyclerItem {
    fun onItemClicked(userDetailModel: UserDetailModel, imageView: View)
}