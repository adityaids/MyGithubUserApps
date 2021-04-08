package com.aditya.mygithubuserapps

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aditya.mygithubuserapps.databinding.ActivityProfileBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.UserDetailModel
import com.aditya.mygithubuserapps.model.UserModel
import com.aditya.mygithubuserapps.viewmodel.ProfileViewModel
import com.bumptech.glide.Glide

class ProfileActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val EXTRA_IMAGE_TRANSITION: String = "extra_image_transition"
        const val EXTRA_DATA: String = "extra_data"
        const val EXTRA_DATA_API: String = "extra_data_api"
    }
    private lateinit var userDetail: UserModel
    private lateinit var userApi: ApiUserModel
    private lateinit var userDetailModel: UserDetailModel
    private lateinit var binding: ActivityProfileBinding
    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Profile"
        profileViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(ProfileViewModel::class.java)

        if (intent.hasExtra(EXTRA_DATA)) {
            userDetail = intent.getParcelableExtra<UserModel>(EXTRA_DATA) as UserModel
            val intRes: Int = resources.getIdentifier(userDetail.avatar, "drawable", packageName)
            val avatar: Drawable? = ContextCompat.getDrawable(this, intRes)
            Glide.with(this)
                    .load(avatar)
                    .into(binding.imgDetailProfile)
            binding.tvDetailName.text = if (userDetail.name != null) userDetail.name else "-"
            binding.tvDetailUsername.text = if (userDetail.userName != null) userDetail.userName else "-"
            binding.tvLocation.text = if (userDetail.location != null) userDetail.location else "-"
            binding.tvCompany.text = if (userDetail.company != null) userDetail.company else "-"
            binding.tvRepository.text = if (userDetail.repository != null) userDetail.repository else "-"
            binding.tvDetailFollower.text = if (userDetail.follower != null) userDetail.follower else "0"
            binding.tvDetailFollowing.text = if (userDetail.following != null) userDetail.following else "0"


            if (userDetail.isFollow) {
                val drawable = ContextCompat.getDrawable(this, R.drawable.ic_check)
                binding.btnFollow.setTextColor(ContextCompat.getColor(this,
                        R.color.text_secondary_color
                ))
                binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                binding.btnFollow.text = resources.getString(R.string.following)
            } else {
                val drawable = ContextCompat.getDrawable(this, R.drawable.ic_add)
                binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.btnFollow.text = resources.getString(R.string.follow)
            }
        } else {
            userDetailModel = intent.getParcelableExtra<UserDetailModel>(EXTRA_DATA_API) as UserDetailModel
            Glide.with(this)
                    .load(userDetailModel.avatarUrl)
                    .into(binding.imgDetailProfile)
            binding.tvDetailUsername.text = userDetailModel.login?:"null"
            binding.tvCompany.text = userDetailModel.company?:"-"
            binding.tvDetailName.text = userDetailModel.name?:"-"
            binding.tvLocation.text = userDetailModel.location?:"-"
            binding.tvRepository.text = userDetailModel.publicRepos?.toString()?: "0"
            binding.tvDetailFollower.text = userDetailModel.followers?.toString()?: "0"
            binding.tvDetailFollowing.text = userDetailModel.following?.toString()?: "0"
            Log.d("hasExtra search", userDetailModel.name.toString())
            if (userDetailModel.isFollow) {
                val drawable = ContextCompat.getDrawable(this, R.drawable.ic_check)
                binding.btnFollow.setTextColor(ContextCompat.getColor(this,
                        R.color.text_secondary_color
                ))
                binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                binding.btnFollow.text = resources.getString(R.string.following)
            } else {
                val drawable = ContextCompat.getDrawable(this, R.drawable.ic_add)
                binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
                binding.btnFollow.text = resources.getString(R.string.follow)
            }
        }

        binding.btnFollow.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_follow -> {
                if (userDetail.isFollow) {
                    val drawable = ContextCompat.getDrawable(this, R.drawable.ic_add)
                    binding.btnFollow.text = resources.getString(R.string.follow)
                    binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
                    binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    Toast.makeText(this, "Unfollow", Toast.LENGTH_LONG).show()
                    userDetail.isFollow = false
                } else {
                    val drawable = ContextCompat.getDrawable(this, R.drawable.ic_check)
                    binding.btnFollow.text = resources.getString(R.string.following)
                    binding.btnFollow.setTextColor(ContextCompat.getColor(this,
                        R.color.text_secondary_color
                    ))
                    binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    Toast.makeText(this, "Following", Toast.LENGTH_LONG).show()
                    userDetail.isFollow = true
                    userDetailModel.isFollow = true
                }
            }
            R.id.btn_share -> {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TITLE, userDetail.name)
                    putExtra(Intent.EXTRA_TEXT, userDetail.repository)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, "Share")
                startActivity(shareIntent)

            }
        }
    }
}