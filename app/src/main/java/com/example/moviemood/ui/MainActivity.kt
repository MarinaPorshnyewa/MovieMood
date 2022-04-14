package com.example.moviemood.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviemood.R
import com.example.moviemood.databinding.ActivityMainBinding
import com.example.moviemood.ui.authorization.LoginViewModel
import com.example.moviemood.ui.authorization.LoginViewModelFactory
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var auth: FirebaseAuth

    private lateinit var viewModel: LoginViewModel

    private lateinit var googleSignInClient: GoogleSignInClient

    @Inject
    lateinit var viewModelProvider: LoginViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth


        MyApplication.appComponent.injectGoogleSignIn(this)


        viewModel = ViewModelProvider(this, viewModelProvider)
            .get(LoginViewModel::class.java)

        navController = findNavController(R.id.navHostFragment)
        navController.graph = navController.navInflater.inflate(R.navigation.main_graph)

        lifecycleScope.launch {
            googleSignInClient = viewModel.getClient()
        }

        viewModel.loadView = {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            if (it == "Registration was completed") {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }

        binding.signInGoogleButton.setOnClickListener {
            signIn()
        }


        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab?.position == 1) {
                    navController.navigate(R.id.registrationFragment)
                    binding.signInGoogleButton.isVisible = false

                } else {
                    navController.navigate(R.id.authorizationFragment)
                    binding.signInGoogleButton.isVisible = true
                }

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                //TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                //TODO("Not yet implemented")
            }

        })
    }

    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("user", user.email)
            startActivity(intent)
        }
    }


    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                viewModel.signInWithGoogle(account.idToken!!)

            } catch (e: ApiException) {

                Toast.makeText(this, "Google sign in failed", Toast.LENGTH_LONG).show()

            }
        }
    }

    companion object {
        private const val TAG = "GoogleActivity"
        private const val RC_SIGN_IN = 9001
    }


}

