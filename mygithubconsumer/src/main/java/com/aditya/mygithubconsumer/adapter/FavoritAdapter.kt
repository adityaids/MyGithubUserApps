package com.aditya.mygithubconsumer.adapter

import android.database.Cursor
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.mygithubconsumer.MainActivity.Companion.COLUMN_AVATAR
import com.aditya.mygithubconsumer.MainActivity.Companion.COLUMN_NAME
import com.aditya.mygithubconsumer.MainActivity.Companion.COLUMN_URL
import com.aditya.mygithubconsumer.databinding.ItemListFavoritBinding
import com.aditya.mygithubconsumer.model.FavoritModel
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target

class FavoritAdapter: RecyclerView.Adapter<FavoritAdapter.FavoritViewHolder>() {

    private val listUser = ArrayList<FavoritModel>()
    private lateinit var onFavoritItemClick: OnClickedFavoriteItem
    private var mCursor: Cursor? = null

    fun setUser(cursor: Cursor?) {
        mCursor = cursor
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
        holder.bind(mCursor, position)
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class FavoritViewHolder(private val binding: ItemListFavoritBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(cursor: Cursor?, position: Int){
            if (cursor != null) {
                if (cursor.moveToPosition(position)) {
                    val urlImage = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR))
                    binding.tvNameFavorit.text =
                        cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                COLUMN_NAME
                            )
                        )
                    Glide.with(itemView.context)
                        .load(urlImage)
                        .into(binding.imgProfileFavorit)
                    val favoritModel = FavoritModel(
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AVATAR)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL))
                    )
                    itemView.setOnClickListener{onFavoritItemClick.onItemClicked(favoritModel)}
                }
            }
        }
    }
}