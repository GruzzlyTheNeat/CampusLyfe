package com.example.campuslyfe.fragment.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.R
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
                        println("succeed")
                        val user = mAuth.currentUser
                        findNavController().navigate(R.id.action_signInPasswordFragment_to_mainActivity)

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

    private fun signIn(eMail: String, password: String) {
        println("mfgdlşfgşldfg")


    }
}