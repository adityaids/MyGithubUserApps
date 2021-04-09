package com.aditya.mygithubuserapps.viewpager

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.aditya.mygithubuserapps.FollowerFragment
import com.aditya.mygithubuserapps.model.ApiUserModel

class ViewPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    private lateinit var userName: String

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = FollowerFragment.newInstance(userName)
            1 -> fragment = FollowerFragment.newInstance(userName)
        }
        return fragment as Fragment
    }

    fun setUserName(mUserName: String){
        userName = mUserName
    }
}