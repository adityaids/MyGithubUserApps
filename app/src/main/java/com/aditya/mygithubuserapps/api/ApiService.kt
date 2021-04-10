package com.aditya.mygithubuserapps.api

import com.aditya.mygithubuserapps.BuildConfig
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.SearchUserModel
import com.aditya.mygithubuserapps.model.UserDetailModel
import retrofit2.Call
import retrofit2.http.*


interface ApiService {
    @GET("users?q=")
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getUserList(@Query("q") username: String): Call<SearchUserModel>

    @GET
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getUserDetail(@Url userDetail: String): Call<UserDetailModel>

    @GET("{username}/followers")
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getFollower(@Path("username") username: String): Call<ArrayList<ApiUserModel>>

    @GET("{username}/following")
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<ApiUserModel>>
}