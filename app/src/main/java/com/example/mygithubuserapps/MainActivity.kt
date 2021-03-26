package com.example.mygithubuserapps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mygithubuserapps.databinding.ActivityMainBinding
import com.example.mygithubuserapps.model.UserHorizontalAdapter
import com.example.mygithubuserapps.model.UserModel
import com.example.mygithubuserapps.model.UserVerticalAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var dataName: Array<String>
    private lateinit var dataUserName: Array<String>
    private lateinit var dataAvatar: Array<String>
    private lateinit var dataCompany: Array<String>
    private lateinit var dataLocation: Array<String>
    private lateinit var dataRepository: Array<String>
    private lateinit var dataFollower: Array<String>
    private lateinit var dataFollowing: Array<String>
    private lateinit var userVerticalAdapter: UserVerticalAdapter
    private lateinit var userHorizontalAdapter: UserHorizontalAdapter
    private var listUser = arrayListOf<UserModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepare()
        addItem()
        showRecycler()
    }

    private fun prepare(){
        dataName = resources.getStringArray(R.array.name)
        dataUserName = resources.getStringArray(R.array.username)
        dataAvatar = resources.getStringArray(R.array.avatar)
        dataCompany = resources.getStringArray(R.array.company)
        dataLocation = resources.getStringArray(R.array.location)
        dataRepository = resources.getStringArray(R.array.repository)
        dataFollowing = resources.getStringArray(R.array.following)
        dataFollower = resources.getStringArray(R.array.followers)
    }

    private fun addItem(){
        for (position in dataName.indices) {
            val userModel = UserModel(
                    dataName[position],
                    dataUserName[position],
                    dataAvatar[position],
                    dataLocation[position],
                    dataCompany[position],
                    dataRepository[position],
                    dataFollowing[position],
                    dataFollower[position]
            )
            listUser.add(userModel)
        }
        userVerticalAdapter = UserVerticalAdapter(listUser)
        userHorizontalAdapter = UserHorizontalAdapter(listUser)
    }

    private fun showRecycler(){
        binding.rvUserVertical.layoutManager = LinearLayoutManager(this)
        binding.rvUserVertical.setHasFixedSize(true)
        binding.rvUserVertical.adapter = userVerticalAdapter

        binding.rvUserHorizontal.layoutManager = LinearLayoutManager(this)
        binding.rvUserHorizontal.setHasFixedSize(true)
        binding.rvUserHorizontal.adapter = userHorizontalAdapter
    }
}