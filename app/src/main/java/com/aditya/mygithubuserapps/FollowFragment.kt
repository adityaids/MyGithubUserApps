package com.aditya.mygithubuserapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.aditya.mygithubuserapps.adapter.UserAdapter
import com.aditya.mygithubuserapps.databinding.FragmentFollowBinding
import com.aditya.mygithubuserapps.model.ApiUserModel
import com.aditya.mygithubuserapps.viewmodel.FollowViewModel

class FollowFragment : Fragment() {

    companion object {
        private const val FOLLOW_ARG = "follow_arg"
        @JvmStatic
        fun newInstance(followList: ArrayList<ApiUserModel>) =
                FollowFragment().apply {
                    arguments = Bundle().apply {
                        putParcelableArrayList(FOLLOW_ARG, followList)
                    }
                }
    }
    private var mBinding: FragmentFollowBinding? = null
    private val binding get() = mBinding!!
    private lateinit var followViewModel: FollowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentFollowBinding.inflate(inflater, container, false)
        followViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowViewModel::class.java)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userList = arguments?.getParcelableArrayList<ApiUserModel>(FOLLOW_ARG)
        val userAdapter = UserAdapter()
        binding.rvFollow.layoutManager = LinearLayoutManager(view.context)
        binding.rvFollow.setHasFixedSize(true)
        binding.rvFollow.adapter = userAdapter
        if (userList != null) {
            userAdapter.setData(userList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}