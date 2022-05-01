package com.example.campuslyfe.fragment.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.databinding.FragmentSignInPasswordBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class SignInPasswordFragment : Fragment() {
    private val args by navArgs<SignInPasswordFragmentArgs>()
    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignInPasswordBinding.inflate(inflater,container,false)

        mAuth = Firebase.auth


        binding.buttonDevamEtPassword.setOnClickListener {
            println(args.eMail)
            val eMail : String = args.eMail.trim()
            val password : String = binding.eTextSighInPassword.text.toString().trim()
            signIn(eMail,password)
        }
        return binding.root



    }

    private fun signIn(eMail: String, password : String){
        println("mfgdlşfgşldfg")
            mAuth.signInWithEmailAndPassword(eMail,password).addOnCompleteListener(this.requireActivity()) { task ->
                if (task.isSuccessful) {
                    println("succeed")
                    val user = mAuth.currentUser

                } else {
                    println(task.exception)
                }

            }

    }
}