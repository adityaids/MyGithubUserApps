package com.aditya.mygithubuserapps.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class SearchUserModel(
		@field:SerializedName("items")
		val items: ArrayList<ApiUserModel>? = null
)

@Parcelize
data class ApiUserModel(

		@field:SerializedName("login")
		val login: String? = null,

		@field:SerializedName("url")
		val url: String,

		@field:SerializedName("avatar_url")
		val avatarUrl: String? = null,
		var isFollow: Boolean = false,
		var isFavorited: Boolean = false

) : Parcelable
