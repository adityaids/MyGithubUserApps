package com.aditya.mygithubconsumer.viewmodel

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aditya.mygithubconsumer.MainActivity
import com.aditya.mygithubconsumer.api.ApiService
import com.aditya.mygithubconsumer.model.FavoritModel
import com.aditya.mygithubconsumer.model.UserDetailModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    companion object {
        const val urlDetailUser: String = "https://api.github.com/users/"
    }
    private val userDetail = MutableLiveData<UserDetailModel>()
    private val errorResponse = MutableLiveData<String>()
    fun getDetailUser(userName: String) {
        var userDetailModel: UserDetailModel
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(urlDetailUser)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(ApiService::class.java)
        val userCall = service.getUserDetail(userName)
        userCall.enqueue(object : Callback<UserDetailModel> {
            override fun onResponse(
                call: Call<UserDetailModel>,
                response: Response<UserDetailModel>
            ) {
                userDetailModel = UserDetailModel(
                    response.body()?.login,
                    response.body()?.name,
                    response.body()?.avatarUrl,
                    response.body()?.location,
                    response.body()?.company,
                    response.body()?.publicRepos,
                    response.body()?.followers,
                    response.body()?.following,
                    false
                )
                userDetail.postValue(userDetailModel)
            }

            override fun onFailure(call: Call<UserDetailModel>, t: Throwable) {
                errorResponse.postValue(t.message)
            }
        })
    }
    fun mapCursorToArrayList(notesCursor: Cursor?): ArrayList<FavoritModel> {
        val notesList = ArrayList<FavoritModel>()

        notesCursor?.apply {
            while (moveToNext()) {
                val name = getString(getColumnIndexOrThrow(MainActivity.COLUMN_NAME))
                val avatar = getString(getColumnIndexOrThrow(MainActivity.COLUMN_AVATAR))
                val url = getString(getColumnIndexOrThrow(MainActivity.COLUMN_URL))
                notesList.add(FavoritModel(name, avatar, url))
            }
        }
        return notesList
    }
    fun getUserDetail(): LiveData<UserDetailModel> {
        return userDetail
    }
    fun getErrorResponse(): LiveData<String> {
        return errorResponse
    }
}