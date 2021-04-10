package com.aditya.mygithubuserapps

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.aditya.mygithubuserapps.databinding.ActivityProfileBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.UserDetailModel
import com.aditya.mygithubuserapps.viewmodel.ProfileViewModel
import com.aditya.mygithubuserapps.viewpager.ViewPagerAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayoutMediator

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val EXTRA_IMAGE_TRANSITION: String = "extra_image_transition"
        const val EXTRA_DATA: String = "extra_data"
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

        if (intent.hasExtra(EXTRA_DATA)) {
            userDetailModel = intent.getParcelableExtra<UserDetailModel>(EXTRA_DATA) as UserDetailModel
            val intRes: Int = resources.getIdentifier(userDetailModel.avatarUrl, "drawable", packageName)
            val avatar: Drawable? = ContextCompat.getDrawable(this, intRes)
            Glide.with(this)
                    .load(avatar)
                    .into(binding.imgDetailProfile)
            binding.tvDetailUsername.text = userDetailModel.login?:"null"
            binding.tvCompany.text = userDetailModel.company?:"-"
            binding.tvDetailName.text = userDetailModel.name?:"-"
            binding.tvLocation.text = userDetailModel.location?:"-"
            binding.tvDetailRepo.text = userDetailModel.publicRepos?.toString()?: "0"
            binding.tvDetailFollower.text = userDetailModel.followers?.toString()?: "0"
            binding.tvDetailFollowing.text = userDetailModel.following?.toString()?: "0"

            if (userDetailModel.isFollow) {
                binding.btnFollow.setTextColor(ContextCompat.getColor(this,
                        R.color.text_secondary_color
                ))
                binding.btnFollow.text = resources.getString(R.string.following)
            } else {
                binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.btnFollow.text = resources.getString(R.string.follow)
            }
            init("dummy")
        } else {
            userDetailModel = intent.getParcelableExtra<UserDetailModel>(EXTRA_DATA_API) as UserDetailModel
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
                binding.btnFollow.setTextColor(ContextCompat.getColor(this,
                        R.color.text_secondary_color
                ))
                binding.btnFollow.text = resources.getString(R.string.following)
            } else {
                binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.btnFollow.text = resources.getString(R.string.follow)
            }
            userDetailModel.login?.let { init(it) }
        }
        binding.btnFollow.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_follow -> {
                if (userDetailModel.isFollow) {
                    binding.btnFollow.text = resources.getString(R.string.follow)
                    binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
                    Toast.makeText(this, "Unfollow", Toast.LENGTH_LONG).show()
                    userDetailModel.isFollow = false
                } else {
                    binding.btnFollow.text = resources.getString(R.string.following)
                    binding.btnFollow.setTextColor(ContextCompat.getColor(this,
                        R.color.text_secondary_color
                    ))
                    Toast.makeText(this, "Following", Toast.LENGTH_LONG).show()
                    userDetailModel.isFollow = true
                }
            }
            R.id.btn_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, userDetailModel.name)
                    putExtra(Intent.EXTRA_TEXT, userDetailModel.publicRepos)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Share")
                startActivity(shareIntent)
            }
        }
    }

    private fun init(userName: String){
        pagerAdapter = ViewPagerAdapter(this)
        pagerAdapter.setUserName(userName)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabHost, binding.viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
        supportActionBar?.elevation = 0f
    }
}