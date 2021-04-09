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
import com.aditya.mygithubuserapps.databinding.FragmentFollowBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.viewmodel.FollowerViewModel
import kotlin.math.log

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
    private var mBinding: FragmentFollowBinding? = null
    private val binding get() = mBinding!!
    private lateinit var followerViewModel: FollowerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFollowBinding.inflate(inflater, container, false)
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
            val userAdapter = UserAdapter()
            binding.rvFollow.layoutManager = LinearLayoutManager(view.context)
            binding.rvFollow.setHasFixedSize(true)
            binding.rvFollow.adapter = userAdapter
            userAdapter.setData(result)
            binding.loadingFollower.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}