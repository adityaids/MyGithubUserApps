package com.aditya.mygithubuserapps.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.aditya.mygithubuserapps.R
import com.aditya.mygithubuserapps.databinding.UserItemsBinding
import com.aditya.mygithubuserapps.model.UserModel
import java.util.ArrayList

class UserVerticalAdapter : RecyclerView.Adapter<UserVerticalAdapter.UserVerticalViewHolder>(){

    private val listUser = ArrayList<UserModel>()
    private lateinit var onItemClickedRecyclerCallback: OnClickedRecyclerItem

    fun setData(items: ArrayList<UserModel>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemClickCallback(onClickedRecyclerItem: OnClickedRecyclerItem){
        this.onItemClickedRecyclerCallback = onClickedRecyclerItem
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
            val intRes: Int = itemView.context.resources.getIdentifier(userModel.avatar, "drawable", itemView.context.packageName)
            val avatar: Drawable? = ContextCompat.getDrawable(itemView.context, intRes)
            binding.tvName.text = userModel.name
            binding.tvUsername.text = userModel.userName
            Glide.with(itemView.context)
                .load(avatar)
                .apply(RequestOptions().override(80, 80))
                .into(binding.imgProfile)
            binding.btnFollow.setOnClickListener {
                if (userModel.isFollow) {
                    binding.btnFollow.text = itemView.context.getString(R.string.follow)
                    binding.btnFollow.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    Toast.makeText(itemView.context, "Unfollow", Toast.LENGTH_SHORT).show()
                    userModel.isFollow = false
                } else {
                    binding.btnFollow.text = itemView.context.getString(R.string.following)
                    binding.btnFollow.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_secondary_color))
                    Toast.makeText(itemView.context, "Followed", Toast.LENGTH_SHORT).show()
                    userModel.isFollow = true
                }
            }
            itemView.setOnClickListener {
                onItemClickedRecyclerCallback.onItemClicked(userModel, binding.imgProfile)
            }
        }
    }
}