package com.example.moviemood.ui.authorization


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviemood.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository,
    private val auth: FirebaseAuth
) : ViewModel() {

    lateinit var loadView: (str: String) -> Unit
    lateinit var toast: (isSuc: Boolean) -> Unit
    private var disposable: Disposable? = null
    var isUserValid = false

    fun signInWithGoogle(idToken: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.signInWithGoogle(idToken).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    if (auth.currentUser != null) {

                        loadView("Registration was completed")
                    }
                } else {
                    loadView("Error")
                }

            }
        }
    }

    //проверяет валидность емейла: не пустой ли, содержит ли нужные символы
    fun validateEmail(email: String): Boolean {
        val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        if (email.isEmpty()) {
            return false
        } else {
            if (email.trim().matches(emailPattern)) {
                return true
            }
        }
        return false
    }

    //проверяет валидность пароля: не пустой ли
    fun validatePassword(password: String): Boolean {
        return password.isNotEmpty()
    }

    //отправляет логин и пароль в файрбейз и ждет результат проверки
    fun firebaseLogin(email: String, password: String) {
        disposable = repository.login(email, password)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                toast(true)
            }, {
                toast(false)
            })
    }

    suspend fun getClient() = repository.getClient()

}