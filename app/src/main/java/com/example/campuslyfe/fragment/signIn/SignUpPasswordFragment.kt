package com.example.campuslyfe.fragment.signIn

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.campuslyfe.activity.MainActivity
import com.example.campuslyfe.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SignUpPasswordFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth

    private val signInSignUpViewModel by sharedViewModel<SignInSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)

        mAuth = Firebase.auth

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = signInSignUpViewModel
            buttonKayitOl.setOnClickListener {
                val password: String = signInSignUpViewModel.password.value?.trim()!!
                val email: String = signInSignUpViewModel.password.value?.trim()!!

                mAuth.createUserWithEmailAndPassword(
                    email, password
                ).addOnCompleteListener(requireActivity()) {
                    if (it.isSuccessful) {
                        startActivity(
                            Intent(requireContext(), MainActivity::class.java)
                        )
                        activity?.finish()
                    } else {
                        Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
                    }
                }

            }
        }

        return binding.root
    }


}