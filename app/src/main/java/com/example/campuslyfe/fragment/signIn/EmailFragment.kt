package com.example.campuslyfe.fragment.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.databinding.FragmentEmailBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EmailFragment : Fragment() {

    private val signInSignUpViewModel by sharedViewModel<SignInSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentEmailBinding.inflate(inflater, container, false).apply {
            viewModel = signInSignUpViewModel
            lifecycleOwner = viewLifecycleOwner

            buttonDevamEtGiris.setOnClickListener {
                if (signInSignUpViewModel.validateEmail.value != true) {
                    signInSignUpViewModel.showEmailError.postValue(true)
                    return@setOnClickListener
                }
                val action =
                    EmailFragmentDirections.actionEmailFragmentToSignInPasswordFragment()
                findNavController().navigate(action)
            }

            buttonKayitOl.setOnClickListener {
                if (signInSignUpViewModel.validateEmail.value != true) {
                    signInSignUpViewModel.showEmailError.postValue(true)
                    return@setOnClickListener
                }
                val action =
                    EmailFragmentDirections.actionEmailFragmentToSignUpFragment()
                findNavController().navigate(action)
            }
        }.root
    }

}