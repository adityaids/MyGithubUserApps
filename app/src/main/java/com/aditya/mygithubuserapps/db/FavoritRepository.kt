package com.aditya.mygithubuserapps.db

import android.database.Cursor
import androidx.annotation.WorkerThread
import com.aditya.mygithubuserapps.model.FavoritModel
import kotlinx.coroutines.flow.Flow

class FavoritRepository(private val favoritDao: FavoritDao) {

    val favoritedUser: Flow<List<FavoritModel>> = favoritDao.getFavoritList()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(favoritModel: FavoritModel) {
        favoritDao.insert(favoritModel)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(favoritModel: FavoritModel) {
        favoritDao.delete(favoritModel)
    }
}