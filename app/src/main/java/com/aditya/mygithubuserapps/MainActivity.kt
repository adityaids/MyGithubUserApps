package com.aditya.mygithubuserapps

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubuserapps.adapter.OnClickedRecyclerItem
import com.aditya.mygithubuserapps.adapter.UserHorizontalAdapter
import com.aditya.mygithubuserapps.adapter.UserVerticalAdapter
import com.aditya.mygithubuserapps.databinding.ActivityMainBinding
import com.aditya.mygithubuserapps.model.UserDetailModel
import com.aditya.mygithubuserapps.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var userVerticalAdapter: UserVerticalAdapter
    private lateinit var userHorizontalAdapter: UserHorizontalAdapter
    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        userHorizontalAdapter = UserHorizontalAdapter()
        userVerticalAdapter = UserVerticalAdapter()
        showRecycler()

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
                MainViewModel::class.java)
        mainViewModel.prepare(this)
        mainViewModel.getListUser().observe(this){ listItem ->
            if (listItem != null) {
                setDataRecycler(listItem)
            }
        }
        binding.btnSearch.setOnClickListener(this)
        binding.btnFavorit.setOnClickListener(this)
        binding.btnSetting.setOnClickListener(this)
        userVerticalAdapter.setOnItemClickCallback(object: OnClickedRecyclerItem {
            override fun onItemClicked(userDetailModel: UserDetailModel, imageView: View) {
                val imagePair = Pair.create(imageView, ProfileActivity.EXTRA_IMAGE_TRANSITION)

                val intent = Intent(this@MainActivity, ProfileActivity::class.java).apply {
                    putExtra(ProfileActivity.EXTRA_DATA, userDetailModel)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val activityOption = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, imagePair)
                    startActivity(intent, activityOption.toBundle())
                } else {
                    startActivity(intent)
                }
            }
        })

        userHorizontalAdapter.setOnItemCLickCallback(object: OnClickedRecyclerItem {
            override fun onItemClicked(userDetailModel: UserDetailModel, imageView: View) {
                val imagePair = Pair.create(imageView, ProfileActivity.EXTRA_IMAGE_TRANSITION)

                val intent = Intent(this@MainActivity, ProfileActivity::class.java).apply {
                    putExtra(ProfileActivity.EXTRA_DATA, userDetailModel)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val activityOption = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, imagePair)
                    startActivity(intent, activityOption.toBundle())
                } else {
                    startActivity(intent)
                }
            }
        })
    }

    private fun showRecycler(){
        binding.rvUserVertical.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = userVerticalAdapter
        }
        binding.rvUserHorizontal.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
            adapter = userHorizontalAdapter
        }
    }

    private fun setDataRecycler(listUser: ArrayList<UserDetailModel>){
        userHorizontalAdapter.setData(listUser)
        userVerticalAdapter.setData(listUser)
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_search -> {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_setting -> {
                val intent = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_favorit -> {
                val intent = Intent(this@MainActivity, FavoritActivity::class.java)
                startActivity(intent)
            }
        }
    }
}