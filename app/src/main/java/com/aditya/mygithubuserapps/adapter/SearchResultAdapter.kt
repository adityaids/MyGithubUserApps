package com.aditya.mygithubuserapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.mygithubuserapps.databinding.SearchResultUserBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.SearchUserModel
import com.bumptech.glide.Glide

class SearchResultAdapter: RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    private val listUser = ArrayList<ApiUserModel>()
    private lateinit var onItemClickedRecyclerCallback: OnClickedApiRecycler

    fun setData(items: ArrayList<ApiUserModel>){
        listUser.clear()
        listUser.addAll(items)
        notifyDataSetChanged()
    }

    fun setOnItemCLickCallback(onCLickRecyclerItem: OnClickedApiRecycler){
        this.onItemClickedRecyclerCallback = onCLickRecyclerItem
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
            itemView.setOnClickListener { onItemClickedRecyclerCallback.onItemClicked(apiUserModel) }
        }
    }
}