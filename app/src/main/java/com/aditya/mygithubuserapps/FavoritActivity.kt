package com.aditya.mygithubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubuserapps.adapter.FavoritAdapter
import com.aditya.mygithubuserapps.adapter.OnClickedFavoriteItem
import com.aditya.mygithubuserapps.databinding.ActivityFavoritBinding
import com.aditya.mygithubuserapps.model.FavoritModel
import com.aditya.mygithubuserapps.viewmodel.FavoritViewModel

class FavoritActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoritBinding
    private lateinit var favoritViewModel: FavoritViewModel
    private lateinit var favoritAdapter: FavoritAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoritBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoritAdapter = FavoritAdapter()
        favoritViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FavoritViewModel::class.java)
        favoritViewModel.getListUser()?.observe(this, ::showRecycler)
        favoritAdapter.setOnFavoritItemCallBack(object : OnClickedFavoriteItem{
            override fun onItemClicked(favoritModel: FavoritModel) {
                favoritViewModel.delete(favoritModel)
            }
        })
    }

    private fun showRecycler(list: List<FavoritModel>?) {
        if (list != null) {
            binding.rvFavorit.layoutManager = LinearLayoutManager(this)
            binding.rvFavorit.setHasFixedSize(true)
            binding.rvFavorit.adapter = favoritAdapter
            favoritAdapter.setData(list as ArrayList<FavoritModel>)
        } else {
            binding.rvFavorit.visibility = View.GONE
            binding.tvFavoritNotif.visibility = View.VISIBLE
        }
    }
}
