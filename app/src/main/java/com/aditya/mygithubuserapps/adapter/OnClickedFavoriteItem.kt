package com.aditya.mygithubuserapps.adapter

import com.aditya.mygithubuserapps.model.FavoritModel

interface OnClickedFavoriteItem {
    fun onItemClicked(favoritModel: FavoritModel)
}