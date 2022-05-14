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
import com.example.campuslyfe.databinding.FragmentSignUpBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class SignUpPasswordFragment : Fragment() {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var mAuth: FirebaseAuth
    private val args by navArgs<SignUpPasswordFragmentArgs>()

    private val signInSignUpViewModel by sharedViewModel<SignInSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = signInSignUpViewModel

        mAuth = Firebase.auth
        binding.buttonKayitOl.setOnClickListener {
            val password: String = binding.eTextSignUpPassword.text.toString().trim()
            val email: String = args.emailKayitOl.trim()
            mAuth.createUserWithEmailAndPassword(
                email, password
            ).addOnCompleteListener(requireActivity(), OnCompleteListener {
                if (it.isSuccessful) {
                    val user = mAuth.currentUser
                    val userEmail = user!!.email.toString()
                    findNavController().navigate(R.id.action_signUpFragment_to_mainActivity)
                } else {
                    Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
                }
            })


        }



        return binding.root
    }


}