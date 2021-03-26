package com.example.mygithubuserapps.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygithubuserapps.model.UserModel

class UserHorizontalAdapter(private val listUser: ArrayList<UserModel>) : RecyclerView.Adapter<UserHorizontalAdapter.UserAdapterHorizontalViewHolder>() {


    inner class UserAdapterHorizontalViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapterHorizontalViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserAdapterHorizontalViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}