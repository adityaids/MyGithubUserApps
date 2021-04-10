package com.aditya.mygithubuserapps

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubuserapps.adapter.UserAdapter
import com.aditya.mygithubuserapps.databinding.FragmentFollowerBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.viewmodel.FollowerViewModel

class FollowerFragment : Fragment() {

    companion object {
        private const val NAME_ARG = "name_arg"
        @JvmStatic
        fun newInstance(userName: String) =
                FollowerFragment().apply {
                    arguments = Bundle().apply {
                        putString(NAME_ARG, userName)
                    }
                }
    }
    private var mBinding: FragmentFollowerBinding? = null
    private val binding get() = mBinding!!
    private lateinit var followerViewModel: FollowerViewModel
    private val userAdapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFollowerBinding.inflate(inflater, container, false)
        binding.rvFollow.layoutManager = LinearLayoutManager(context)
        binding.rvFollow.setHasFixedSize(true)
        binding.rvFollow.adapter = userAdapter
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userName = arguments?.getString(NAME_ARG)
        followerViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowerViewModel::class.java)
        if (userName != null) {
            followerViewModel.getFollower(userName)
            Log.d("username follower", userName)
        }
        followerViewModel.getFollowerList().observe(viewLifecycleOwner){result->
            Log.d("observer follower", result.get(0).toString())
            userAdapter.setData(result)
            binding.loadingFollower.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}