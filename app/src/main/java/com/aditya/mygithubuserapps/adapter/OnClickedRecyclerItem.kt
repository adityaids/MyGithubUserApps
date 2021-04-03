package com.aditya.mygithubuserapps.adapter

import android.view.View
import com.aditya.mygithubuserapps.model.UserModel

interface OnClickedRecyclerItem {
    fun onItemClicked(userModel: UserModel, imageView: View)
}