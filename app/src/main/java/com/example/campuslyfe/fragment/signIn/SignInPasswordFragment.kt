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
import com.example.campuslyfe.utils.StateResource
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignInPasswordFragment : Fragment() {

    private val signInSignUpViewModel by sharedViewModel<SignInSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignInPasswordBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = signInSignUpViewModel
            eTextSighInPassword.setOnEditorActionListener { _, _, _ ->
                buttonDevamEtPassword.performClick()
            }
        }

        signInSignUpViewModel.signInState.observe(viewLifecycleOwner) {
            when (it) {
                is StateResource.Loading -> Unit
                is StateResource.Success -> {
                    startActivity(
                        Intent(requireContext(), MainActivity::class.java)
                    )
                    activity?.finish()
                }
                is StateResource.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Bu bilgilere sahip kullanıcı bulunmamaktadır",
                        Toast.LENGTH_LONG
                    ).show()
                    println(it.e)
                }
            }
        }
        return binding.root
    }
}