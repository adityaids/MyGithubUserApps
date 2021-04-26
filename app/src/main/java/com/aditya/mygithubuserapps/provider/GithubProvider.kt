package com.aditya.mygithubuserapps.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.model.FavoritModel.Companion.TABLE_NAME


class GithubProvider : ContentProvider() {

    companion object {
        private const val FAVORIT_ID = 1
        private const val AUTHORITY = "com.aditya.mygithubuserapps.provider"
        private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        init {
            sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORIT_ID)
        }
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        val code: Int = sUriMatcher.match(uri)
        val cursor: Cursor
        val context: Context? = context
        val db: FavoritDatabase? = context?.let { FavoritDatabase.getDatabase(it) }
        val mFavoritDao = db?.favoritDao()
        return if (code == FAVORIT_ID) {
            cursor = mFavoritDao?.selectAll()!!
            cursor.setNotificationUri(context.getContentResolver(), uri)
            cursor
        } else {
            throw IllegalArgumentException("Unknown URI: $uri")
        }
    }

    override fun getType(uri: Uri): String? {
        TODO("Implement this to handle requests for the MIME type of the data" +
                "at the given URI")
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
    }

    override fun update(uri: Uri, values: ContentValues?, selection: String?,
                        selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to update one or more rows.")
    }
}