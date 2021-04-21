package com.aditya.mygithubuserapps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.model.FavoritModel

class FavoritViewModel(db: FavoritDatabase): ViewModel() {
    private val favoritDao = db.favoritDao()
    private val listUser = favoritDao?.getFavoritList()

    fun delete(favoritModel: FavoritModel){
        favoritDao?.delete(favoritModel)
    }

    fun getListUser(): LiveData<List<FavoritModel>>? {
        return listUser
    }
}