package com.example.mygithubuserapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mygithubuserapps.R
import com.example.mygithubuserapps.databinding.UserItemsBinding
import com.example.mygithubuserapps.model.UserModel
import java.util.ArrayList

class UserVerticalAdapter : RecyclerView.Adapter<UserVerticalAdapter.UserVerticalViewHolder>(){

    private val listUser = ArrayList<UserModel>()
    private lateinit var onItemClickedCallback: OnClickedItem

    fun setData(items: ArrayList<UserModel>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    fun SetOnItemClickCallback(onClickedItem: OnClickedItem){
        this.onItemClickedCallback = onClickedItem
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): UserVerticalViewHolder = UserVerticalViewHolder(
            UserItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: UserVerticalViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class UserVerticalViewHolder(private val binding : UserItemsBinding)
        :RecyclerView.ViewHolder(binding.root) {
        fun bind(userModel: UserModel){
            binding.tvName.text = userModel.name
            binding.tvUsername.text = userModel.userName
            Glide.with(itemView.context)
                .load(userModel.avatar)
                .apply(RequestOptions().override(80, 80))
                .into(binding.imgProfil)
            binding.btnFollow.setOnClickListener {
                binding.btnFollow.text = "Followed"
                binding.btnFollow.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_secondary_color))
                Toast.makeText(itemView.context, "Followed", Toast.LENGTH_SHORT).show()
            }
            itemView.setOnClickListener {
                onItemClickedCallback.onItemClicked(userModel, binding.imgProfil)
            }
        }
    }
}