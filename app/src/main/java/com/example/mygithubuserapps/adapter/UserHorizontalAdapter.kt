package com.example.mygithubuserapps.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mygithubuserapps.R
import com.example.mygithubuserapps.databinding.UserItemsHorizontalBinding
import com.example.mygithubuserapps.model.UserModel

class UserHorizontalAdapter : RecyclerView.Adapter<UserHorizontalAdapter.UserHorizontalViewHolder>() {

    private val listUser = ArrayList<UserModel>()

    fun setData(items: ArrayList<UserModel>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): UserHorizontalViewHolder =
            UserHorizontalViewHolder(
                    UserItemsHorizontalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

    override fun onBindViewHolder(holder: UserHorizontalViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }


    inner class UserHorizontalViewHolder(private val binding: UserItemsHorizontalBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel){
            Glide.with(itemView.context)
                .load(userModel.avatar)
                .apply(RequestOptions().override(80, 80))
                .into(binding.imgProfilHorizontal)
            binding.tvNameHorizontal.text = userModel.name
            binding.tvUsernameHorizontal.text = userModel.userName
        }
    }
}