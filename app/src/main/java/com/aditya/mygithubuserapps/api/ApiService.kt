package com.aditya.mygithubuserapps.api

import com.aditya.mygithubuserapps.BuildConfig
import com.aditya.mygithubuserapps.model.UserDetail
import com.aditya.mygithubuserapps.model.UserList
import com.aditya.mygithubuserapps.model.UserModel
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("users?q=")
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getUserList(@Query("q") username: String): Call<UserList>

    @GET
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getUserDetail(@Url userDetail: String): Call<UserDetail>

    @GET("{username}/followers")
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getFollower(@Path("username") username: String): Call<ArrayList<UserModel>>

    @GET("{username}/following")
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<UserModel>>
}