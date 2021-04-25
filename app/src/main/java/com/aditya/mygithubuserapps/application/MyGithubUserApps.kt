package com.aditya.mygithubuserapps.application

import android.app.Application
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.db.FavoritRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MyGithubUserApps: Application() {

    val database by lazy { FavoritDatabase.getDatabase(this) }
    val repository by lazy { FavoritRepository(database.favoritDao()) }
}