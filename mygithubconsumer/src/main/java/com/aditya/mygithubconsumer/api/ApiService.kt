package com.aditya.mygithubconsumer.api

import com.aditya.mygithubconsumer.BuildConfig
import com.aditya.mygithubconsumer.model.FavoritModel
import com.aditya.mygithubconsumer.model.UserDetailModel
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getUserDetail(@Url userDetail: String): Call<UserDetailModel>

    @GET("{username}/followers")
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getFollower(@Path("username") username: String): Call<ArrayList<FavoritModel>>

    @GET("{username}/following")
    @Headers("Authorization: token<" + BuildConfig.API_KEY + ">")
    fun getFollowing(@Path("username") username: String): Call<ArrayList<FavoritModel>>
}