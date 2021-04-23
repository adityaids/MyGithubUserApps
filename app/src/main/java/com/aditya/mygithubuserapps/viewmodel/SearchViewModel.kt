package com.aditya.mygithubuserapps.viewmodel

import android.app.Application
import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.*
import com.aditya.mygithubuserapps.api.ApiService
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.db.FavoritRepository
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.FavoritModel
import com.aditya.mygithubuserapps.model.SearchUserModel
import com.aditya.mygithubuserapps.model.UserDetailModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SearchViewModel(private val repository: FavoritRepository) : ViewModel() {

    companion object {
        const val urlSearch : String = "https://api.github.com/search/"
        const val urlDetailUser: String = "https://api.github.com/users/"
    }
    private val listSearchUser = MutableLiveData<ArrayList<ApiUserModel>>()
    private val userDetail = MutableLiveData<UserDetailModel>()
    private val errorResponse = MutableLiveData<String>()
    private val dbResponse = MutableLiveData<String>()

    fun setQuerySearch(q: String){
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(urlSearch)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiService::class.java)
        val userListCall = service.getUserList(q)
        userListCall.enqueue(object : Callback<SearchUserModel?> {
            override fun onResponse(
                call: Call<SearchUserModel?>?,
                response: Response<SearchUserModel?>
            ) {
                if (response.body() != null) {
                    val responseList = ArrayList<ApiUserModel>()
                    response.body()?.items?.let { responseList.addAll(it) }
                    listSearchUser.postValue(responseList)
                }
            }

            override fun onFailure(call: Call<SearchUserModel?>, t: Throwable) {
                errorResponse.postValue(t.message)
            }
        })
    }

    fun getDetailUser(userName: String, isFollow: Boolean) {
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
                    isFollow
                )
                userDetail.postValue(userDetailModel)
            }

            override fun onFailure(call: Call<UserDetailModel>, t: Throwable) {
                errorResponse.postValue(t.message)
            }
        })
    }

    fun insertDb(apiUserModel: ApiUserModel) = viewModelScope.launch{
        val user = FavoritModel(
            apiUserModel.login?:"-",
            apiUserModel.avatarUrl?:"-",
            apiUserModel.url,
            apiUserModel.isFavorited,
            apiUserModel.isFollow
        )
        repository.insert(user)
    }

    fun deleteDb(apiUserModel: ApiUserModel)= viewModelScope.launch{
        val user = FavoritModel(
            apiUserModel.login?:"-",
            apiUserModel.avatarUrl?:"-",
            apiUserModel.url,
            apiUserModel.isFavorited,
            apiUserModel.isFollow
        )
        repository.delete(user)
    }

    fun getListSearchUser(): LiveData<ArrayList<ApiUserModel>> {
        return listSearchUser
    }
    fun getUserDetail(): LiveData<UserDetailModel>{
        return userDetail
    }
    fun getErrorResponse(): LiveData<String>{
        return errorResponse
    }
    fun getDbResponse(): LiveData<String>{
        return dbResponse
    }
}