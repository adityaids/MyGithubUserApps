package com.example.mygithubuserapps

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.example.mygithubuserapps.databinding.ActivityProfilBinding
import com.example.mygithubuserapps.model.UserModel

class ProfilActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        const val EXTRA_IMAGE_TRANSITION: String = "extra_image_transition"
        const val EXTRA_DATA: String = "extra_data"
    }
    private lateinit var userDetail: UserModel
    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Profile"
        userDetail = intent.getParcelableExtra<UserModel>(EXTRA_DATA) as UserModel
        Glide.with(this)
            .load(userDetail.avatar)
            .into(binding.imgDetailProfile)
        binding.tvDetailName.text = if (userDetail.name != null) userDetail.name else "-"
        binding.tvDetailUsername.text = if (userDetail.userName != null) userDetail.userName else "-"
        binding.tvLocation.text = if (userDetail.location != null) userDetail.location else "-"
        binding.tvCompany.text = if (userDetail.company != null) userDetail.company else "-"
        binding.tvRepository.text = if (userDetail.repository != null) userDetail.repository else "-"
        binding.tvDetailFollower.text = if (userDetail.follower != null) userDetail.follower else "0"
        binding.tvDetailFollowing.text = if (userDetail.following != null) userDetail.following else "0"


        if (userDetail.isFavorited) {
            val drawable = ContextCompat.getDrawable(this, R.drawable.ic_check)
            binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.text_secondary_color))
            binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            binding.btnFollow.text = resources.getString(R.string.following)
        } else {
            val drawable = ContextCompat.getDrawable(this, R.drawable.ic_add)
            binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
            binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
            binding.btnFollow.text = resources.getString(R.string.follow)
        }

        binding.btnFollow.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_follow -> {
                if (userDetail.isFavorited) {
                    val drawable = ContextCompat.getDrawable(this, R.drawable.ic_add)
                    binding.btnFollow.text = resources.getString(R.string.follow)
                    binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.white))
                    binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    Toast.makeText(this, "Unfollow", Toast.LENGTH_LONG).show()
                    userDetail.isFavorited = false
                } else {
                    val drawable = ContextCompat.getDrawable(this, R.drawable.ic_check)
                    binding.btnFollow.text = resources.getString(R.string.following)
                    binding.btnFollow.setTextColor(ContextCompat.getColor(this, R.color.text_secondary_color))
                    binding.btnFollow.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null)
                    Toast.makeText(this, "Following", Toast.LENGTH_LONG).show()
                    userDetail.isFavorited = true
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