package com.aditya.mygithubconsumer.adapter

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aditya.mygithubconsumer.R
import com.aditya.mygithubconsumer.databinding.SearchResultUserBinding
import com.aditya.mygithubconsumer.model.ApiUserModel
import com.bumptech.glide.Glide

class UserAdapter: RecyclerView.Adapter<UserAdapter.SearchResultViewHolder>() {

    private lateinit var onItemClickedRecyclerCallback: OnClickedSearchUser
    private lateinit var onFavoritItemClickCallback: OnClickedSearchUser
    var listUser= ArrayList<ApiUserModel>()
        set(listUser) {
            this.listUser.clear()
            this.listUser.addAll(listUser)
            notifyDataSetChanged()
        }

    fun setOnItemCLickCallback(onClickRecyclerItem: OnClickedSearchUser){
        this.onItemClickedRecyclerCallback = onClickRecyclerItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder =
        SearchResultViewHolder(
            SearchResultUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        val user = listUser[position]
        holder.bind(user, position)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class SearchResultViewHolder(private val binding: SearchResultUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(apiUserModel: ApiUserModel, position: Int){
            Glide.with(itemView.context)
                .load(apiUserModel.avatarUrl)
                .into(binding.imgProfileSearch)
            binding.tvNameSearch.text = apiUserModel.login
            binding.btnFollowSearch.setOnClickListener {
                if (apiUserModel.isFollow) {
                    binding.btnFollowSearch.text = itemView.context.getString(R.string.follow)
                    binding.btnFollowSearch.setTextColor(ContextCompat.getColor(itemView.context, R.color.white))
                    Toast.makeText(itemView.context, "Unfollow", Toast.LENGTH_SHORT).show()
                    apiUserModel.isFollow = false
                } else {
                    binding.btnFollowSearch.text = itemView.context.getString(R.string.following)
                    binding.btnFollowSearch.setTextColor(ContextCompat.getColor(itemView.context, R.color.text_secondary_color))
                    Toast.makeText(itemView.context, "Followed", Toast.LENGTH_SHORT).show()
                    apiUserModel.isFollow = true
                }
            }
            itemView.setOnClickListener { onItemClickedRecyclerCallback.onItemClicked(apiUserModel) }
        }
    }
}