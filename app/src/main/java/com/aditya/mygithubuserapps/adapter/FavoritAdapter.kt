package com.aditya.mygithubuserapps.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.mygithubuserapps.databinding.ItemListFavoritBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.FavoritModel
import com.bumptech.glide.Glide

class FavoritAdapter: RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {

    private val listUser = ArrayList<FavoritModel>()
    private lateinit var onFavoritItemClick: OnClickedFavoriteItem

    fun setData(items: ArrayList<FavoritModel>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnFavoritItemCallBack(onClickedFavoriteItem: OnClickedFavoriteItem){
        this.onFavoritItemClick = onClickedFavoriteItem
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoritViewHolder =
        FavoritViewHolder(
            ItemListFavoritBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: FavoritViewHolder, position: Int) {
        val user = listUser.get(position)
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class FavoritViewHolder(private val binding: ItemListFavoritBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoritModel: FavoritModel){
            binding.tvNameFavorit.text = favoritModel.nama
            Glide.with(itemView.context)
                .load(favoritModel.avatarUrl)
                .into(binding.imgProfileFavorit)
            binding.btnDeleteFavorit.setOnClickListener {
                listUser.remove(favoritModel)
                notifyDataSetChanged()
                onFavoritItemClick.onItemClicked(favoritModel)
            }
        }
    }
}