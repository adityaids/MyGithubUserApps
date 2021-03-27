package com.example.mygithubuserapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Switch
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.mygithubuserapps.databinding.ActivityProfilBinding
import com.example.mygithubuserapps.model.UserModel

class ProfilActivity : AppCompatActivity(), View.OnClickListener {

    companion object{
        val EXTRA_IMAGE_TRANSITION: String = "extra_image_transition"
        val EXTRA_DATA: String = "extra_data"
    }
    private lateinit var userDetail: UserModel
    private lateinit var binding: ActivityProfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

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

        binding.btnFollow.setOnClickListener(this)
        binding.btnShare.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_follow -> Toast.makeText(this, "Following", Toast.LENGTH_LONG).show()
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