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
import com.example.campuslyfe.utils.StateResource
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SignUpPasswordFragment : Fragment() {

    private val signInSignUpViewModel by sharedViewModel<SignInSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSignUpBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = signInSignUpViewModel
            eTextSignUpPassword.setOnEditorActionListener { _, _, _ ->
                buttonKayitOl.performClick()
            }

            signInSignUpViewModel.signUpState.observe(viewLifecycleOwner) {
                when (it) {
                    is StateResource.Loading -> Unit
                    is StateResource.Success -> {
                        startActivity(
                            Intent(requireContext(), MainActivity::class.java)
                        )
                        activity?.finish()
                    }
                    is StateResource.Error -> {
                        Toast.makeText(requireContext(), "Failed", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        return binding.root
    }


}