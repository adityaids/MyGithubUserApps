package com.example.mygithubuserapps.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mygithubuserapps.model.UserModel
import java.util.ArrayList

class UserVerticalAdapter(private val listUser: ArrayList<UserModel>) : RecyclerView.Adapter<UserVerticalAdapter.UserVerticalViewHolder>(){

    override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
    ): UserVerticalViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: UserVerticalViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return listUser.size
    }

    inner class UserVerticalViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}