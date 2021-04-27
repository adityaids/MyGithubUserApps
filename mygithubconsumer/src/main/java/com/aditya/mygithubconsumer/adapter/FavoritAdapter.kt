package com.aditya.mygithubconsumer.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.mygithubconsumer.databinding.ItemListFavoritBinding
import com.aditya.mygithubconsumer.model.FavoritModel
import com.bumptech.glide.Glide

class FavoritAdapter: RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {

    private lateinit var onFavoritItemClick: OnClickedFavoriteItem
    var mListUser= ArrayList<FavoritModel>()
        set(listUser) {
            this.mListUser.clear()
            this.mListUser.addAll(listUser)
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
        val user = mListUser[position]
        holder.bind(user)
    }

    override fun getItemCount(): Int {
        return mListUser.size
    }

    inner class FavoritViewHolder(private val binding: ItemListFavoritBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(favoritModel: FavoritModel){
            binding.tvNameFavorit.text = favoritModel.nama
            Glide.with(itemView.context)
                    .load(favoritModel.avatarUrl)
                    .into(binding.imgProfileFavorit)
            itemView.setOnClickListener{onFavoritItemClick.onItemClicked(favoritModel)}
        }
    }
}