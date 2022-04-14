package com.example.moviemood.ui.registration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.moviemood.R
import com.example.moviemood.databinding.FragmentRegistrationBinding
import com.example.moviemood.ui.HomeActivity
import com.example.moviemood.ui.MyApplication
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationFragment : Fragment() {

    private lateinit var binding: FragmentRegistrationBinding

    private lateinit var navController: NavController

    private lateinit var viewModel: RegistrationViewModel

    @Inject
    lateinit var registrationViewModelFactory: RegistrationViewModelFactory

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegistrationBinding.inflate(inflater)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MyApplication.appComponent.injectRegistration(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = view.findNavController()

        viewModel = registrationViewModelFactory.createViewModel()

        val email = binding.registrationFragmentTextFieldEmail
        val password = binding.registrationFragmentTextFieldPassword
        val passwordRepeat = binding.registrationFragmentTextFieldPasswordRepeat



        binding.registrationFragmentButtonRegistration.setOnClickListener {
            email.error = null
            password.error = null
            passwordRepeat.error = null
            val emailText = email.editText?.text.toString()
            val passwordText = password.editText?.text.toString()
            val passwordRepeatText = passwordRepeat.editText?.text.toString()
            when (viewModel.validation(emailText, passwordText, passwordRepeatText)) {
                Status.ERROR_VALIDATION_EMAIL -> email.error = getString(R.string.Incorrect_email)
                Status.ERROR_VALIDATION_PASSWORD -> password.error =
                    getString(R.string.Password_length)
                Status.ERROR_VALIDATION_PASSWORD_REPEAT -> passwordRepeat.error =
                    getString(R.string.Does_not_match)
                Status.SUCCESS -> {
                    registrationUser(emailText, passwordText, binding)
                }
            }
        }
    }

    private fun registrationUser(
        emailText: String,
        passwordText: String,
        binding: FragmentRegistrationBinding
    ) {
        lifecycleScope.launch {
            viewModel.registrationUser(emailText, passwordText).observe(viewLifecycleOwner) {
                when (it.status) {
                    Status.SUCCESS -> {
                        val intent = Intent(context, HomeActivity::class.java)
                        startActivity(intent)
                    }
                    Status.ERROR -> {
                        binding.registrationFragmentTextFieldEmail.error =
                            getString(R.string.User_email_error)
                    }
                }
            }
        }
    }
}