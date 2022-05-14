package com.example.campuslyfe.fragment.signIn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.campuslyfe.activity.MainActivity
import com.example.campuslyfe.databinding.FragmentSignInPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignInPasswordFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth

    private val signInSignUpViewModel by sharedViewModel<SignInSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignInPasswordBinding.inflate(inflater, container, false)

        mAuth = Firebase.auth

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = signInSignUpViewModel

        binding.buttonDevamEtPassword.setOnClickListener {
            val eMail: String = signInSignUpViewModel.email.value?.trim()!!
            val password: String = signInSignUpViewModel.password.value?.trim()!!
            mAuth.signInWithEmailAndPassword(eMail, password)
                .addOnCompleteListener(this.requireActivity()) { task ->
                    if (task.isSuccessful) {
                        startActivity(
                            Intent(requireContext(), MainActivity::class.java)
                        )
                        activity?.finish()
                    } else {
                        Toast.makeText(
                            requireContext(),
                            "Bu bilgilere sahip kullanıcı bulunmamaktadır",
                            Toast.LENGTH_LONG
                        ).show()
                        println(task.exception)
                    }

                }

        }
        return binding.root
    }
}