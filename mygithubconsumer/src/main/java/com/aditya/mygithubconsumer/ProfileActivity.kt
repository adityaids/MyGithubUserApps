package com.aditya.mygithubconsumer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import com.aditya.mygithubconsumer.databinding.ActivityProfileBinding
import com.aditya.mygithubconsumer.model.UserDetailModel
import com.aditya.mygithubconsumer.viewpager.ViewPagerAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

class ProfileActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USER: String = "extra_user"
        const val EXTRA_DATA_API: String = "extra_data_api"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.follower,
            R.string.following
        )
    }
    private lateinit var userDetailModel: UserDetailModel
    private lateinit var binding: ActivityProfileBinding
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Profile"

        userDetailModel = intent.getParcelableExtra<UserDetailModel>(EXTRA_USER) as UserDetailModel
        init(userDetailModel)
        initAdapter(userDetailModel.login.toString())
    }
    fun init(userDetailModel: UserDetailModel){
        Glide.with(this)
            .load(userDetailModel.avatarUrl)
            .into(binding.imgDetailProfile)
        binding.tvDetailUsername.text = userDetailModel.login?:"null"
        binding.tvCompany.text = userDetailModel.company?:"-"
        binding.tvDetailName.text = userDetailModel.name?:"-"
        binding.tvLocation.text = userDetailModel.location?:"-"
        binding.tvDetailRepo.text = userDetailModel.publicRepos?.toString()?: "0"
        binding.tvDetailFollower.text = userDetailModel.followers?.toString()?: "0"
        binding.tvDetailFollowing.text = userDetailModel.following?.toString()?: "0"

        if (userDetailModel.isFollow) {
            binding.btnFollow.setTextColor(
                ContextCompat.getColor(this,
                    R.color.text_secondary_color
                ))
            binding.btnFollow.text = resources.getString(R.string.following)
        } else {
            binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.btnFollow.text = resources.getString(R.string.follow)
        }
    }
    private fun initAdapter(userName: String){
        pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter.setUserName(userName)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabHost, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
}