package com.example.moviemood.ui.authorization

import com.example.moviemood.repository.LoginRepository
import com.example.moviemood.ui.base.BaseViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class LoginViewModelFactory @Inject constructor(
    private val repository: LoginRepository,
    private val firebaseAuth: FirebaseAuth
) : BaseViewModelProvider<LoginViewModel>(LoginViewModel::class.java) {

    override fun createViewModel(): LoginViewModel {
        return LoginViewModel(repository, firebaseAuth)
    }
}