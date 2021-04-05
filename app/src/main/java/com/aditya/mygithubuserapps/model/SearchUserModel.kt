package com.aditya.mygithubuserapps.model

import com.google.gson.annotations.SerializedName

data class SearchUserModel(
	@field:SerializedName("items")
	val items: ArrayList<ApiUserModel>? = null
)

data class ApiUserModel(

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("url")
	val url: String? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null
)
