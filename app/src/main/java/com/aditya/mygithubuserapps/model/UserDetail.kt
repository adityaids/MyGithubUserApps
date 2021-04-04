package com.aditya.mygithubuserapps.model

data class UserDetail(
    var userName: String?,
    var name: String?,
    var avatar: Int,
    var location: String?,
    var company: String?,
    var repository: String?,
    var following: String?,
    var follower: String?,
    var isFavorited: Boolean = false
)
