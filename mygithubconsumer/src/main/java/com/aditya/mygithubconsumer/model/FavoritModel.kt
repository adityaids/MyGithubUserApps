package com.aditya.mygithubconsumer.model

import android.os.Parcelable
import android.provider.BaseColumns
import kotlinx.parcelize.Parcelize

@Parcelize
class FavoritModel (
    var nama: String,
    var avatarUrl: String,
    var url: String
): Parcelable