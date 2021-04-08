package com.aditya.mygithubuserapps

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Pair
import android.view.View
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubuserapps.adapter.OnClickedApiRecycler
import com.aditya.mygithubuserapps.adapter.OnClickedRecyclerItem
import com.aditya.mygithubuserapps.adapter.SearchResultAdapter
import com.aditya.mygithubuserapps.databinding.ActivitySearchBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.model.UserDetailModel
import com.aditya.mygithubuserapps.model.UserModel
import com.aditya.mygithubuserapps.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchBinding
    private lateinit var searchViewModel: SearchViewModel
    private lateinit var searchResultAdapter: SearchResultAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        searchViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(SearchViewModel::class.java)
        searchResultAdapter = SearchResultAdapter()
        binding.rvSearch.layoutManager = LinearLayoutManager(this)
        binding.rvSearch.setHasFixedSize(true)
        binding.rvSearch.adapter = searchResultAdapter

        searchResultAdapter.setOnItemCLickCallback(object : OnClickedApiRecycler{
            override fun onItemClicked(apiUserModel: ApiUserModel) {
                searchViewModel.getDetailUser(apiUserModel.url)
            }
        })
        searchViewModel.getUserDetail().observe(this){ result->
            Log.d("observe userDetail", result.name.toString())
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
                searchResultAdapter.setData(result)
            } else {
                binding.searchingLoading.visibility = View.GONE
                binding.tvSearchNotif.visibility = View.VISIBLE
            }
        }
    }
}