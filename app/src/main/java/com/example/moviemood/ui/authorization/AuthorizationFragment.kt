package com.example.moviemood.ui.authorization

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviemood.R
import com.example.moviemood.databinding.FragmentAuthorizationBinding
import com.example.moviemood.ui.HomeActivity
import com.example.moviemood.ui.MyApplication
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class AuthorizationFragment : Fragment() {

    @Inject
    lateinit var loginViewModelFactory: LoginViewModelFactory

    @Inject
    lateinit var auth: FirebaseAuth

    private lateinit var binding: FragmentAuthorizationBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: LoginViewModel
    private lateinit var loginString: String
    private lateinit var passwordString: String


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAuthorizationBinding.inflate(inflater)
        MyApplication.appComponent.injectLogin(this)
        viewModel = loginViewModelFactory.create(LoginViewModel::class.java)
        checkAndStartMainFragment()
        setLoginListener()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = view.findNavController()
    }

    //слушатель кнопки login
    private fun setLoginListener() {
        binding.authorizationFragmentButtonLogin.setOnClickListener {
            if (validateLogin()) {
                if (validatePassword()) {
                    viewModel.firebaseLogin(loginString, passwordString)
                }
            }
        }
    }

    //проверяет авторизацию и принимает решение о запуске MainActivity если пользователь авторизирован
    private fun checkAndStartMainFragment() {
        viewModel.toast = {
            Toast.makeText(
                requireContext(),
                if (it) {
                    startActivity(Intent(context, HomeActivity::class.java))
                    getString(R.string.hello_with_name) + auth.currentUser?.email
                } else {
                    getString(R.string.something_wrong)
                }, Toast.LENGTH_LONG
            ).show()
        }
    }

    //проверяет, подходит ли логин
    private fun validateLogin(): Boolean {
        val login = binding.authorizationFragmentTextFieldLogin
        loginString = login.editText?.text.toString()
        val isEmailOk = viewModel.validateEmail(loginString)
        return if (!isEmailOk) {
            login.error = getString(R.string.Incorrect_email)
            false
        } else true
    }

    //проверяет, не пустой ли пароль
    private fun validatePassword(): Boolean {
        val password = binding.authorizationFragmentTextFieldPassword
        passwordString = password.editText?.text.toString()
        val isPasswordOk = viewModel.validatePassword(passwordString)
        return if (!isPasswordOk) {
            password.error = getString(R.string.Password_length)
            false
        } else true
    }
}