package com.example.moviemood.ui.registration

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemood.repository.RegistrationRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class RegistrationViewModel(
    private val repository: RegistrationRepository
) : ViewModel() {

    private val userLiveData = MutableLiveData<Resource>()


    fun validation(email: String, password: String, passwordRepeat: String): Status {
        return when {
            email.isEmpty() -> Status.ERROR_VALIDATION_EMAIL
            password.isEmpty() -> Status.ERROR_VALIDATION_PASSWORD
            passwordRepeat.isEmpty() -> Status.ERROR_VALIDATION_PASSWORD_REPEAT
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> Status.ERROR_VALIDATION_EMAIL
            password.length <= 8 -> Status.ERROR_VALIDATION_PASSWORD
            password != passwordRepeat -> Status.ERROR_VALIDATION_PASSWORD_REPEAT
            else -> Status.SUCCESS
        }
    }

    suspend fun registrationUser(
        email: String,
        password: String
    ): LiveData<Resource> {
        repository.verificationUser(email).addOnCompleteListener {
            if (it.result?.signInMethods?.size == 0) {
                viewModelScope.launch {
                    repository.registrationUser(email, password).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            userLiveData.postValue(Resource.success())
                        } else {
                            userLiveData.postValue(Resource.error())
                        }
                    }
                }
            } else userLiveData.postValue(Resource.error())
        }
        return userLiveData
    }
}