package com.aditya.mygithubuserapps.model

import com.google.gson.annotations.SerializedName

class UserList {
        @SerializedName("items")
        private var resultUser = ArrayList<UserModel>()

        fun getResultUser() : ArrayList<UserModel> {
                return resultUser
        }
}