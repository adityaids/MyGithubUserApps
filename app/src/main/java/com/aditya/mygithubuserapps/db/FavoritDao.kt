package com.aditya.mygithubuserapps.db

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.*
import com.aditya.mygithubuserapps.model.FavoritModel


@Dao
interface FavoritDao {
    @Query("Select * from " + FavoritModel.TABLE_NAME)
    fun getFavoritList(): LiveData<List<FavoritModel?>?>?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favoritModel: FavoritModel?)

    @Delete
    fun delete(favoritModel: FavoritModel?)

    @Query("SELECT * FROM " + FavoritModel.TABLE_NAME)
    fun selectAll(): Cursor?
}