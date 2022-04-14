package com.example.moviemood.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviemood.R
import com.example.moviemood.databinding.ActivityFavoritesBinding

class FavoritesActivity: AppCompatActivity() {

    private lateinit var binding: ActivityFavoritesBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityFavoritesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.navHomeFragment)
        navController.graph = navController.navInflater.inflate(R.navigation.favorites_graph)
    }
}