package com.aditya.mygithubconsumer

import android.content.Intent
import android.database.ContentObserver
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.provider.BaseColumns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubconsumer.adapter.FavoritAdapter
import com.aditya.mygithubconsumer.adapter.OnClickedFavoriteItem
import com.aditya.mygithubconsumer.databinding.ActivityMainBinding
import com.aditya.mygithubconsumer.model.FavoritModel
import com.aditya.mygithubconsumer.model.UserDetailModel
import com.aditya.mygithubconsumer.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    companion object {
        private const val AUTHORITY = "com.aditya.mygithubuserapps"
        private const val SCHEME = "content"
        private const val TABLE_NAME = "favorit"
        private const val COLUMN_ID = BaseColumns._ID
        const val COLUMN_NAME = "nama"
        const val COLUMN_AVATAR = "avatar"
        const val COLUMN_URL = "url"
        const val EXTRA_STATE = "extra"
    }
    private lateinit var favoritAdapter: FavoritAdapter
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private var favoritList = ArrayList<FavoritModel>()
    private val uri: Uri = Uri.Builder().scheme(SCHEME)
        .authority(AUTHORITY)
        .appendPath(TABLE_NAME)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoritAdapter = FavoritAdapter()
        binding.rvFavorit.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = favoritAdapter
        }
        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)
        val myObserver = object : ContentObserver(handler) {
            override fun onChange(self: Boolean) {
                loadNotesAsync()
            }
        }
        contentResolver.registerContentObserver(uri, true, myObserver)
        favoritAdapter.setOnFavoritItemCallBack(object : OnClickedFavoriteItem{
            override fun onItemClicked(favoritModel: FavoritModel) {
                mainViewModel.getDetailUser(favoritModel.url)
                binding.toProfileLoading.visibility = View.VISIBLE
            }
        })
        mainViewModel.getUserDetail().observe(this, ::toProfileAcitivity)
        mainViewModel.getErrorResponse().observe(this, ::showMessage)
        if (savedInstanceState == null) {
            loadNotesAsync()
        } else {
            savedInstanceState.getParcelableArrayList<FavoritModel>(EXTRA_STATE)?.also { favoritAdapter.mListUser = favoritList}
        }

    }

    private fun toProfileAcitivity(detailModel: UserDetailModel){
        intent = Intent(this@MainActivity, ProfileActivity::class.java).apply {
            putExtra(ProfileActivity.EXTRA_USER, detailModel)
        }
        startActivity(intent)
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun loadNotesAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredNotes = async(Dispatchers.IO) {
                val cursor = contentResolver.query(uri, null, null, null, null)
                mainViewModel.mapCursorToArrayList(cursor)
            }
            favoritList = deferredNotes.await()
            if (favoritList.size > 0) {
                favoritAdapter.mListUser = favoritList
            } else {
                binding.rvFavorit.visibility = View.GONE
            }
        }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(EXTRA_STATE, favoritList)
    }
}