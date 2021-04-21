package com.aditya.mygithubuserapps.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.aditya.mygithubuserapps.R
import com.aditya.mygithubuserapps.databinding.SearchResultUserBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.bumptech.glide.Glide

class UserAdapter: RecyclerView.Adapter<UserAdapter.SearchResultViewHolder>() {

    private val listUser = ArrayList<ApiUserModel>()
    private lateinit var onItemClickedRecyclerCallback: OnClickedApiRecycler
    private lateinit var onFavoritItemClickCallback: OnClickedFavoriteItem

    fun setData(items: ArrayList<ApiUserModel>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemCLickCallback(onClickRecyclerItem: OnClickedApiRecycler){
        this.onItemClickedRecyclerCallback = onClickRecyclerItem
    }

    fun setOnFavoritItemCallBack(onClickedFavoriteItem: OnClickedFavoriteItem){
        this.onFavoritItemClickCallback = onClickedFavoriteItem
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
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class SearchResultViewHolder(private val binding: SearchResultUserBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(apiUserModel: ApiUserModel){
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
            binding.btnSearchFavorit.setOnClickListener {
                if (apiUserModel.isFavorited) {
                    val drawable: Drawable? = ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_32)
                    binding.btnSearchFavorit.setCompoundDrawables(null, drawable, null, null)
                    apiUserModel.isFavorited = false
                    onFavoritItemClickCallback.onItemClicked(apiUserModel)
                } else {
                    val drawable: Drawable? = ContextCompat.getDrawable(itemView.context, R.drawable.ic_favorite_32_red)
                    binding.btnSearchFavorit.setCompoundDrawables(null, drawable, null, null)
                    apiUserModel.isFavorited = true
                    onFavoritItemClickCallback.onItemClicked(apiUserModel)
                }
            }
            itemView.setOnClickListener { onItemClickedRecyclerCallback.onItemClicked(apiUserModel) }
        }
    }
}