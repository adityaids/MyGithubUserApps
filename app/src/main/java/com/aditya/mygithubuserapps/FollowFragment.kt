package com.aditya.mygithubuserapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.aditya.mygithubuserapps.databinding.FragmentFollowBinding
import com.aditya.mygithubuserapps.viewmodel.FollowViewModel

class FollowFragment : Fragment() {

    companion object {
        private const val FOLLOW_ARG = "follow_arg"
        @JvmStatic
        fun newInstance(userName: String) =
                FollowFragment().apply {
                    arguments = Bundle().apply {
                        putString(FOLLOW_ARG, userName)
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
        val userName = arguments?.getString(FOLLOW_ARG)
        if (userName != "null") {
            if (userName != null) {
                followViewModel.getFollower(userName)
                followViewModel.getFollowing(userName)
            }
        }
        followViewModel.getFollowerList().observe(viewLifecycleOwner){listFollower->

        }

        followViewModel.getFollowingList().observe(viewLifecycleOwner){listFollowing->

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mBinding = null
    }
}