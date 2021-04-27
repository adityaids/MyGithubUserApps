package com.aditya.mygithubuserapps.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.aditya.mygithubuserapps.db.FavoritDatabase


class GithubProvider : ContentProvider() {

    companion object {
        private const val FAVORIT_ID = 1
        private const val AUTHORITY = "com.aditya.mygithubuserapps"
        private const val TABLE_NAME = "favorit"
        private const val SCHEME = "content"
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var favoritDatabase: FavoritDatabase
        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORIT_ID)
        }
    }
    val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor {
        return when (sUriMatcher.match(uri)) {
            FAVORIT_ID -> favoritDatabase.favoritDao().selectAll()
            else -> throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}