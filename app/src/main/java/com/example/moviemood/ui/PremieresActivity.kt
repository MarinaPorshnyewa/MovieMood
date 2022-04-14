package com.example.moviemood.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviemood.R
import com.example.moviemood.databinding.ActivityHomeBinding
import com.example.moviemood.databinding.ActivityPremieresBinding
import com.example.moviemood.di.AppComponent

class PremieresActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPremieresBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityPremieresBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.navHomeFragment)
        navController.graph = navController.navInflater.inflate(R.navigation.premieres_graph)
    }
}