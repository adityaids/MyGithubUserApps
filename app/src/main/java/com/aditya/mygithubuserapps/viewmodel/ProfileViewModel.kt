package com.aditya.mygithubuserapps.viewmodel

import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    companion object{
        const val urlUserFollower: String = "https://api.github.com/users/{username}/followers"
        const val urlUserFollowing: String = "https://api.github.com/users/{username}/following"
    }


}