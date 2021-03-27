package com.example.mygithubuserapps

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Pair
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuserapps.adapter.OnClickedRecyclerItem
import com.example.mygithubuserapps.databinding.ActivityMainBinding
import com.example.mygithubuserapps.adapter.UserHorizontalAdapter
import com.example.mygithubuserapps.model.UserModel
import com.example.mygithubuserapps.adapter.UserVerticalAdapter
import com.example.mygithubuserapps.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

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

        mainViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MainViewModel::class.java)
        mainViewModel.prepare(this)
        mainViewModel.getListUser().observe(this){ listItem ->
            if (listItem != null) {
                userHorizontalAdapter.setData(listItem)
                userVerticalAdapter.setData(listItem)
                showRecycler()
            }
        }

        userVerticalAdapter.SetOnItemClickCallback(object: OnClickedRecyclerItem{
            override fun onItemClicked(userModel: UserModel, imageView: View) {
                val imagePair = Pair.create(imageView, ProfilActivity.EXTRA_IMAGE_TRANSITION)

                val intent = Intent(this@MainActivity, ProfilActivity::class.java).apply {
                    putExtra(ProfilActivity.EXTRA_DATA, data)
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    val activityOption = ActivityOptions.makeSceneTransitionAnimation(this@MainActivity, imagePair)
                    startActivity(intent, activityOption.toBundle())
                } else {
                    startActivity(intent)
                }
            }
        })

        userHorizontalAdapter.SetOnItemCLickCallback(object: OnClickedRecyclerItem{
            override fun onItemClicked(userModel: UserModel, imageView: View) {
                val imagePair = Pair.create(imageView, ProfilActivity.EXTRA_IMAGE_TRANSITION)

                val intent = Intent(this@MainActivity, ProfilActivity::class.java).apply {
                    putExtra(ProfilActivity.EXTRA_DATA, data)
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
        binding.rvUserVertical.layoutManager = LinearLayoutManager(this)
        binding.rvUserVertical.setHasFixedSize(true)
        binding.rvUserVertical.adapter = userVerticalAdapter

        binding.rvUserHorizontal.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvUserHorizontal.setHasFixedSize(true)
        binding.rvUserHorizontal.adapter = userHorizontalAdapter
    }
}