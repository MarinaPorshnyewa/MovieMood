package com.example.moviemood.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviemood.R
import com.example.moviemood.databinding.ActivityTopBinding
import com.example.moviemood.ui.top.TopFilmsFragment
import com.google.android.material.tabs.TabLayout

class TopActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTopBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        navController = findNavController(R.id.navTopFragment)
//        navController.graph = navController.navInflater.inflate(R.navigation.top_graph)

//        navController = findNavController(R.id.navTopFragment)
//        navController.graph = navController.navInflater.inflate(R.navigation.top_films_graph)
        supportFragmentManager.beginTransaction().add(R.id.navTopFragment, TopFilmsFragment(1)).commit()
        setTabsListeners()
    }

    private fun setTabsListeners() {
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val count = when(tab?.position){
                    1 -> 2
                    2 -> 3
                    else -> 1
                }
                supportFragmentManager.beginTransaction().replace(R.id.navTopFragment, TopFilmsFragment(count)).commit()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }
        })
    }
}