package com.aditya.mygithubconsumer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.aditya.mygithubconsumer.databinding.ActivityProfileBinding
import com.aditya.mygithubconsumer.model.UserDetailModel
import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_USER: String = "extra_user"
    }
    private lateinit var userDetailModel: UserDetailModel
    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Profile"

        userDetailModel = intent.getParcelableExtra<UserDetailModel>(EXTRA_USER) as UserDetailModel
        init(userDetailModel)
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
}