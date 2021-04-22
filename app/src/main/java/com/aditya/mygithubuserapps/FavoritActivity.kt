package com.aditya.mygithubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubuserapps.adapter.FavoritAdapter
import com.aditya.mygithubuserapps.adapter.OnClickedFavoriteItem
import com.aditya.mygithubuserapps.application.GithubApplication
import com.aditya.mygithubuserapps.databinding.ActivityFavoritBinding
import com.aditya.mygithubuserapps.model.FavoritModel
import com.aditya.mygithubuserapps.viewmodel.FavoritViewModel

class FavoritActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritBinding
    private val favoritViewModel: FavoritViewModel by viewModels {
        FavoritViewModel.FavoritViewModelFactory((application as GithubApplication).repository)
    }
    private lateinit var favoritAdapter: FavoritAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoritAdapter = FavoritAdapter()
        favoritViewModel.listFavorit.observe(this, ::showRecycler)
        favoritAdapter.setOnFavoritItemCallBack(object : OnClickedFavoriteItem{
            override fun onItemClicked(favoritModel: FavoritModel) {
                favoritViewModel.delete(favoritModel)
            }
        })
    }

    private fun showRecycler(list: List<FavoritModel>?) {
        if (list != null) {
            favoritAdapter.setData(list as ArrayList<FavoritModel>)
            binding.rvFavorit.apply {
                layoutManager = LinearLayoutManager(this@FavoritActivity)
                setHasFixedSize(true)
                adapter = favoritAdapter
            }
        } else {
            binding.rvFavorit.visibility = View.GONE
            binding.tvFavoritNotif.visibility = View.VISIBLE
        }
    }
}
