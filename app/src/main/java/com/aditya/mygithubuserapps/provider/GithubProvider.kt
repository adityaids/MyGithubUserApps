package com.aditya.mygithubuserapps.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.model.FavoritModel.Companion.TABLE_NAME

class GithubProvider : ContentProvider() {

    companion object {
        private const val FAVORIT_ID = 1
        private const val AUTHORITY = "com.aditya.mygithubuserapps"
    }
    private val sUriMatcher = UriMatcher(UriMatcher.NO_MATCH)
    private lateinit var favoritDatabase: FavoritDatabase
    init {
        sUriMatcher.addURI(AUTHORITY, TABLE_NAME, FAVORIT_ID)
    }

    override fun onCreate(): Boolean {
        return true
    }

    override fun query(uri: Uri, projection: Array<String>?, selection: String?,
                       selectionArgs: Array<String>?, sortOrder: String?): Cursor? {
        return when (sUriMatcher.match(uri)){
            FAVORIT_ID -> favoritDatabase.favoritDao().selectAll()
            else -> null
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