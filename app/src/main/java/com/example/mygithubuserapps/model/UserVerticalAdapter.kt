package com.example.mygithubuserapps.model

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList

class UserVerticalAdapter(private val listUser: ArrayList<UserModel>) : RecyclerView.Adapter<UserVerticalAdapter.UserViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserVerticalAdapter.UserViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserVerticalAdapter.UserViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}