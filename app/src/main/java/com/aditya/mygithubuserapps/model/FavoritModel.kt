package com.aditya.mygithubuserapps.model

import android.provider.BaseColumns
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = FavoritModel.TABLE_NAME)
class FavoritModel(
    @ColumnInfo(name = COLUMN_NAME) var nama: String,
    @ColumnInfo(name = COLUMN_AVATAR) var avatarUrl: String,
    @ColumnInfo(name = COLUMN_URL) var url: String,
    @ColumnInfo(name = COLUMN_FOLLOW) var isFollow: Boolean,
    @ColumnInfo(name = COLUMN_FAVORIT) var isFavorited: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(index = true, name = COLUMN_ID)
    var id = 0

    companion object {
        const val TABLE_NAME = "favorit"
        private const val COLUMN_ID = BaseColumns._ID
        private const val COLUMN_NAME = "nama"
        private const val COLUMN_AVATAR = "avatar"
        private const val COLUMN_URL = "url"
        private const val COLUMN_FAVORIT = "favorit"
        private const val COLUMN_FOLLOW = "follow"
    }
}