package com.example.mygithubuserapps.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
        var userName: String?,
        var name: String?,
        var avatar: Int?,
        var location: String?,
        var company: String?,
        var repository: String?,
        var following: Int?,
        var follower: Int?
) : Parcelable