package com.aditya.mygithubconsumer.adapter

import com.aditya.mygithubconsumer.model.FavoritModel

interface OnClickedFavoriteItem {
    fun onItemClicked(favoritModel: FavoritModel)
}