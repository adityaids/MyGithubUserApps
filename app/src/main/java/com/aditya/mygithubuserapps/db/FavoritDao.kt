package com.aditya.mygithubuserapps.db

import android.database.Cursor
import androidx.room.*
import com.aditya.mygithubuserapps.model.FavoritModel
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoritDao {
    @Query("Select * from " + FavoritModel.TABLE_NAME)
    fun getFavoritList(): Flow<List<FavoritModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(favoritModel: FavoritModel?)

    @Delete
    suspend fun delete(favoritModel: FavoritModel?)

    @Query("SELECT * FROM " + FavoritModel.TABLE_NAME)
    fun selectAll(): Cursor
}