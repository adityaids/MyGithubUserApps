package com.aditya.mygithubuserapps.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aditya.mygithubuserapps.model.FavoritModel
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = [FavoritModel::class], exportSchema = false, version = 1)
abstract class FavoritDatabase : RoomDatabase() {
    abstract fun favoritDao(): FavoritDao

    companion object {
        @Volatile
        private var INSTANCE: FavoritDatabase? = null

        fun getDatabase(
                context: Context,
                scope: CoroutineScope
        ): FavoritDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoritDatabase::class.java,
                        "favorit_database"
                )
                        .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}