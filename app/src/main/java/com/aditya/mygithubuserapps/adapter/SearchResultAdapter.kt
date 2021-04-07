package com.aditya.mygithubuserapps.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aditya.mygithubuserapps.databinding.SearchResultUserBinding

class SearchResultAdapter: RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchResultViewHolder =
        SearchResultViewHolder(
        SearchResultUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: SearchResultViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    inner class SearchResultViewHolder(private val binding: SearchResultUserBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }
}