package com.example.moviemood.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviemood.R
import com.example.moviemood.databinding.ActivityHomeBinding
import com.example.moviemood.ui.home.HomeFragmentDirections
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.navHomeFragment)
        navController.graph = navController.navInflater.inflate(R.navigation.home_graph)

        binding.topAppBar.setNavigationOnClickListener {
            binding.drawerLayout.open()
        }

        binding.navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_item_top -> {
                    //  navController.graph = navController.navInflater.inflate(R.navigation.top_graph)
                    //startActivity(Intent(this, TopActivity::class.java))
                    //menuItem.isChecked = false
                }
                R.id.menu_item_premieres -> {
                    navController.graph = navController.navInflater.inflate(R.navigation.premieres_graph)
                }
                R.id.menu_item_home -> {
                    navController.graph = navController.navInflater.inflate(R.navigation.home_graph)
                }
                R.id.menu_item_favorites -> {
                    navController.graph = navController.navInflater.inflate(R.navigation.favorites_graph)
                }
            }
            menuItem.isChecked = true
            binding.drawerLayout.close()
            true
        }

        val navigationView = binding.navigationView
        val headerView = navigationView.getHeaderView(0)
        val navUsername = headerView.findViewById<View>(R.id.email) as TextView
        navUsername.text = intent.getStringExtra("user")
        getDeeplink()

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        getDeeplink()
    }

    private fun getDeeplink() {
        intent?.data?.getQueryParameter("id")?.let {
            navController.navigate(HomeFragmentDirections.actionToInfoFilmsFragment(it.toInt()))
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_top -> {
                //navController.graph = navController.navInflater.inflate(R.navigation.top_graph)
                return true
            }
            else -> return false
        }
    }
}