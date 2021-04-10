package com.aditya.mygithubuserapps

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubuserapps.adapter.OnClickedApiRecycler
import com.aditya.mygithubuserapps.adapter.UserAdapter
import com.aditya.mygithubuserapps.databinding.ActivitySearchBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    companion object{
        const val STATE: String = "state"
    }
    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        actionBar?.title = "Search"
        binding.searchView.setQuery(savedInstanceState?.getString(STATE)?:"", false)
        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)
        userAdapter = UserAdapter()
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.adapter = userAdapter

        userAdapter.setOnItemCLickCallback(object : OnClickedApiRecycler{
            override fun onItemClicked(apiUserModel: ApiUserModel) {
                searchViewModel.getDetailUser(apiUserModel.url, apiUserModel.isFollow)
            }
        })
        searchViewModel.getUserDetail().observe(this){ result->
            if (result != null) {
                val intent = Intent(this@SearchActivity, ProfileActivity::class.java).apply {
                    putExtra(ProfileActivity.EXTRA_DATA_API, result)
                }
                startActivity(intent)
            }
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchViewModel.setQuerySarch(query)
                    binding.searchingLoading.visibility = View.VISIBLE
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        searchViewModel.getListSearchUser().observe(this){result->
            if (result != null) {
                binding.searchingLoading.visibility = View.GONE
                userAdapter.setData(result)
            } else {
                binding.searchingLoading.visibility = View.GONE
                binding.tvSearchNotif.visibility = View.VISIBLE
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(STATE, binding.searchView.query.toString())
    }
}