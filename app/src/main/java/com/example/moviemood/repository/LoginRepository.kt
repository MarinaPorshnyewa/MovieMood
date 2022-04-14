package com.example.moviemood.repository


import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val googleSignInClient: GoogleSignInClient,
    private val auth: FirebaseAuth
) {


    suspend fun signInWithGoogle(idToken: String) =
        auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))

    suspend fun getClient() = googleSignInClient

    //получаем объект обсервабл от файрбейза и подписываемся на наблюдение
    fun login(email: String, pass: String): Observable<FirebaseUser> {
        return BehaviorSubject.create<FirebaseUser>().apply {
            auth.signInWithEmailAndPassword(
                email, pass
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    auth.currentUser?.let { onNext(it) }
                    onComplete()
                } else {
                    onError(Exception(task.exception))
                }
            }
        }.subscribeOn(Schedulers.io())
    }
}