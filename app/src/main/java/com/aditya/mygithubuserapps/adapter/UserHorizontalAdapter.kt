package com.aditya.mygithubuserapps.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aditya.mygithubuserapps.databinding.UserItemsHorizontalBinding
import com.aditya.mygithubuserapps.model.UserDetailModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class UserHorizontalAdapter : RecyclerView.Adapter<UserHorizontalAdapter.UserHorizontalViewHolder>() {

    private val listUser = ArrayList<UserDetailModel>()
    private lateinit var onItemClickedRecyclerCallback: OnClickedRecyclerItem

    fun setData(items: ArrayList<UserDetailModel>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemCLickCallback(onCLickRecyclerItem: OnClickedRecyclerItem){
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
        fun bind(userDetailModel: UserDetailModel){
            val intRes: Int = itemView.context.resources.getIdentifier(userDetailModel.avatarUrl,
                    "drawable", itemView.context.packageName)
            val avatar: Drawable? = ContextCompat.getDrawable(itemView.context, intRes)
            Glide.with(itemView.context)
                    .load(avatar)
                    .apply(RequestOptions().override(80, 80))
                    .into(binding.imgProfileHorizontal)
            binding.tvNameHorizontal.text = userDetailModel.name
            binding.tvUsernameHorizontal.text = userDetailModel.login

            itemView.setOnClickListener { onItemClickedRecyclerCallback.onItemClicked(userDetailModel, binding.imgProfileHorizontal) }
        }
    }
}