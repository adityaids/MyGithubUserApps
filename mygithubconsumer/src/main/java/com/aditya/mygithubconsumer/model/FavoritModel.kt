package com.aditya.mygithubconsumer.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class FavoritModel (
    var nama: String,
    var avatarUrl: String,
    var url: String
): Parcelable