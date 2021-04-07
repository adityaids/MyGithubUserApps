package com.aditya.mygithubuserapps.model

import com.google.gson.annotations.SerializedName

data class UserDetailModel(

	@field:SerializedName("login")
	val login: String? = null,

	@field:SerializedName("company")
	val company: Any? = null,

	@field:SerializedName("public_repos")
	val publicRepos: Int? = null,

	@field:SerializedName("followers")
	val followers: Int? = null,

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("following")
	val following: Int? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("location")
	val location: String? = null,
)
