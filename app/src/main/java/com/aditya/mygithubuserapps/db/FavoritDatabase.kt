package com.aditya.mygithubuserapps.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.RoomDatabase
import com.aditya.mygithubuserapps.model.FavoritModel
import kotlinx.coroutines.CoroutineScope
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors


@Database(entities = arrayOf(FavoritModel::class), exportSchema = false, version = 1)
abstract class FavoritDatabase : RoomDatabase() {
    abstract fun favoritDao(): FavoritDao?

    companion object {
        private const val DB_NAME = "db_favorit"
        private var instance: FavoritDatabase? = null
        private const val NUMBER_OF_THREADS = 4
        val databaseWriteExecutor: ExecutorService = Executors.newFixedThreadPool(
            NUMBER_OF_THREADS
        )

        @Synchronized
        fun getInstance(context: Context): FavoritDatabase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    FavoritDatabase::class.java, DB_NAME
                )
                    .build()
            }
            return instance
        }
    }
}