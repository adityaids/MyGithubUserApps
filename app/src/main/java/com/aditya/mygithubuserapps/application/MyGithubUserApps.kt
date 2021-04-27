package com.aditya.mygithubuserapps.application

import android.app.Application
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.db.FavoritRepository

class MyGithubUserApps: Application() {

    val database by lazy { FavoritDatabase.getDatabase(this) }
    val repository by lazy { FavoritRepository(database.favoritDao()) }
}