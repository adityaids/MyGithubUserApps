package com.aditya.mygithubuserapps.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aditya.mygithubuserapps.FollowFragment
import com.aditya.mygithubuserapps.model.ApiUserModel

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private val mUserList = ArrayList<ApiUserModel>()

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return FollowFragment.newInstance(mUserList)
    }

    fun setUserList(userList: ArrayList<ApiUserModel>){
        mUserList.addAll(userList)
    }
}