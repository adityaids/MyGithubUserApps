package com.example.mygithubuserapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygithubuserapps.databinding.UserItemsHorizontalBinding
import com.example.mygithubuserapps.model.UserModel

class UserHorizontalAdapter(private val listUser: ArrayList<UserModel>) : RecyclerView.Adapter<UserHorizontalAdapter.UserHorizontalViewHolder>() {

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

        }
    }
}