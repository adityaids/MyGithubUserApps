package com.example.mygithubuserapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mygithubuserapps.databinding.UserItemsHorizontalBinding
import com.example.mygithubuserapps.model.UserModel

class UserHorizontalAdapter : RecyclerView.Adapter<UserHorizontalAdapter.UserHorizontalViewHolder>() {

    private val listUser = ArrayList<UserModel>()
    private lateinit var onItemClickedRecyclerCallback: OnClickedRecyclerItem

    fun setData(items: ArrayList<UserModel>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    fun SetOnItemCLickCallback(onCLickRecyclerItem: OnClickedRecyclerItem){
        this.onItemClickedRecyclerCallback = onCLickRecyclerItem
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

            itemView.setOnClickListener { onItemClickedRecyclerCallback.onItemClicked(userModel, binding.imgProfilHorizontal) }
        }
    }
}