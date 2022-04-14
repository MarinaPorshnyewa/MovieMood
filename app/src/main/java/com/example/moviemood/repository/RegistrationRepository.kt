package com.example.moviemood.repository

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class RegistrationRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    suspend fun registrationUser(email : String, password: String) =
        firebaseAuth.createUserWithEmailAndPassword(email, password)

    suspend fun verificationUser(email: String) = firebaseAuth.fetchSignInMethodsForEmail(email)

}
