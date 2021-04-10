package com.aditya.mygithubuserapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubuserapps.adapter.OnClickedApiRecycler
import com.aditya.mygithubuserapps.adapter.UserAdapter
import com.aditya.mygithubuserapps.databinding.FragmentFollowerBinding
import com.aditya.mygithubuserapps.databinding.FragmentFollowingBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.viewmodel.FollowerViewModel
import com.aditya.mygithubuserapps.viewmodel.FollowingViewModel


class FollowingFragment : Fragment() {

    companion object {
        private const val NAME_ARG = "name_arg"
        @JvmStatic
        fun newInstance(userName: String) =
                FollowingFragment().apply {
                    arguments = Bundle().apply {
                        putString(NAME_ARG, userName)
                    }
                }
    }
    private var mBinding: FragmentFollowingBinding? = null
    private val binding get() = mBinding!!
    private lateinit var followingViewModel: FollowingViewModel
    private val userAdapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFollowingBinding.inflate(inflater, container, false)
        binding.rvFollow.layoutManager = LinearLayoutManager(context)
        binding.rvFollow.setHasFixedSize(true)
        binding.rvFollow.adapter = userAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = arguments?.getString(NAME_ARG)
        followingViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingViewModel::class.java)
        if (userName != null) {
            if (!userName.equals("dummy")) {
                followingViewModel.getFollowing(userName)
                followingViewModel.getFollowingList().observe(viewLifecycleOwner){result->
                    if (result != null) {
                        userAdapter.setData(result)
                        binding.loadingFollowing.visibility = View.GONE
                    } else {
                        binding.loadingFollowing.visibility = View.GONE
                        binding.rvFollow.visibility = View.GONE
                        binding.tvFollowingNotif.visibility = View.VISIBLE
                    }
                }
                userAdapter.setOnItemCLickCallback(object : OnClickedApiRecycler{
                    override fun onItemClicked(apiUserModel: ApiUserModel) {
                        followingViewModel.getDetailUser(apiUserModel.url, apiUserModel.isFollow)
                    }
                })
                followingViewModel.getUserDetail().observe(viewLifecycleOwner){result->
                    if (result != null) {
                        val intent = Intent(context, ProfileActivity::class.java).apply {
                            putExtra(ProfileActivity.EXTRA_DATA_API, result)
                        }
                        startActivity(intent)
                    }
                }
            } else {
                binding.loadingFollowing.visibility = View.GONE
                binding.rvFollow.visibility = View.GONE
                binding.tvFollowingNotif.visibility = View.VISIBLE
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}