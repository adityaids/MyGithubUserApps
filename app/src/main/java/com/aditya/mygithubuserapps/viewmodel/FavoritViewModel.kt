package com.aditya.mygithubuserapps.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.aditya.mygithubuserapps.db.FavoritDao
import com.aditya.mygithubuserapps.db.FavoritDatabase
import com.aditya.mygithubuserapps.db.FavoritRepository
import com.aditya.mygithubuserapps.model.FavoritModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class FavoritViewModel(private val repository: FavoritRepository): ViewModel() {

    val listFavorit: LiveData<List<FavoritModel>> = repository.favoritedUser.asLiveData()

    fun delete(favoritModel: FavoritModel) = viewModelScope.launch{
        repository.delete(favoritModel)
    }
}
class FavoritViewModelFactory(private val repository: FavoritRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FavoritViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FavoritViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}