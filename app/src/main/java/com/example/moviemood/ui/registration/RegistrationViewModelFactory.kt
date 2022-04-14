package com.example.moviemood.ui.registration

import com.example.moviemood.repository.RegistrationRepository
import com.example.moviemood.ui.base.BaseViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegistrationViewModelFactory @Inject constructor(
    private val repository: RegistrationRepository
) : BaseViewModelProvider<RegistrationViewModel>(RegistrationViewModel::class.java) {

    override fun createViewModel(): RegistrationViewModel {
        return RegistrationViewModel(repository)
    }
}
