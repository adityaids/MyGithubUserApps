package com.aditya.mygithubuserapps.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.model.FavoritModel

class FavoritViewModel: ViewModel() {

    fun delete(favoritModel: FavoritModel, application: Application){
        val db = FavoritDatabase.getInstance(application)
        val favoritDao = db?.favoritDao()
        FavoritDatabase.databaseWriteExecutor.execute {
            favoritDao?.delete(favoritModel)
        }
    }

    fun getListUser(application: Application): LiveData<List<FavoritModel>>? {
        val db = FavoritDatabase.getInstance(application)
        val favoritDao = db?.favoritDao()
        return favoritDao?.getFavoritList()
    }
}