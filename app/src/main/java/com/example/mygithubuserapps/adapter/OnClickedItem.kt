package com.example.mygithubuserapps.adapter

import android.view.View
import com.example.mygithubuserapps.model.UserModel

interface OnClickedItem {
    fun onItemClicked(userModel: UserModel, imageView: View)
}