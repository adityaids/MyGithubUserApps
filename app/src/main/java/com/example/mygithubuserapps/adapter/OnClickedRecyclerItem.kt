package com.example.mygithubuserapps.adapter

import android.view.View
import com.example.mygithubuserapps.model.UserModel

interface OnClickedRecyclerItem {
    fun onItemClicked(userModel: UserModel, imageView: View)
}