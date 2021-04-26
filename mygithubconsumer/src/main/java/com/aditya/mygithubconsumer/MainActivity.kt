package com.aditya.mygithubconsumer

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubconsumer.adapter.FavoritAdapter
import com.aditya.mygithubconsumer.adapter.OnClickedFavoriteItem
import com.aditya.mygithubconsumer.databinding.ActivityMainBinding
import com.aditya.mygithubconsumer.model.FavoritModel
import com.aditya.mygithubconsumer.model.UserDetailModel
import com.aditya.mygithubconsumer.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    companion object {
        private val URI: Uri = Uri.parse("content://com.aditya.mygithubuserapps/favorit/1")
        private const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_NAME = "nama"
        const val COLUMN_AVATAR = "avatar"
        const val COLUMN_URL = "url"
    }
    private lateinit var favoritAdapter: FavoritAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        favoritAdapter = FavoritAdapter()
        binding.rvFavorit.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = favoritAdapter
        }
        LoaderManager.getInstance(this).initLoader(1, null, mLoaderCallbacks)
        favoritAdapter.setOnFavoritItemCallBack(object : OnClickedFavoriteItem{
            override fun onItemClicked(favoritModel: FavoritModel) {
                mainViewModel.getDetailUser(favoritModel.url)
            }
        })
        mainViewModel.getUserDetail().observe(this, ::toProfileAcitivity)
        mainViewModel.getErrorResponse().observe(this, ::showMessage)
    }

    private val mLoaderCallbacks: LoaderManager.LoaderCallbacks<Cursor?> =
        object : LoaderManager.LoaderCallbacks<Cursor?> {
            override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor?> {
                return CursorLoader(
                    applicationContext,
                    URI, arrayOf(COLUMN_ID, COLUMN_NAME, COLUMN_AVATAR, COLUMN_URL),
                    null, null, null
                )
            }

            override fun onLoadFinished(loader: Loader<Cursor?>, data: Cursor?) {
                if (data != null) {
                    favoritAdapter.setUser(data)
                    Log.d("cursor", data.getString(1).toString())
                }
            }

            override fun onLoaderReset(loader: Loader<Cursor?>) {
                favoritAdapter.setUser(null)
            }
        }

    private fun toProfileAcitivity(detailModel: UserDetailModel){
        intent = Intent(this@MainActivity, ProfileActivity::class.java).apply {
            putExtra(ProfileActivity.EXTRA_USER, detailModel)
        }
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}