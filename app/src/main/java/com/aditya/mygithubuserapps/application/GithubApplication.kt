package com.aditya.mygithubuserapps.application

import android.app.Application
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.db.FavoritRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class GithubApplication: Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { FavoritDatabase.getDatabase(this, applicationScope) }
    val repository by lazy { FavoritRepository(database.favoritDao()) }
}