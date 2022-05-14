package com.example.campuslyfe.fragment.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.databinding.FragmentEmailBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class EmailFragment : Fragment() {

    private val signInSignUpViewModel by sharedViewModel<SignInSignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentEmailBinding.inflate(inflater, container, false)
        val buttonGirisEmail: Button = binding.ButtondevamEtGiris
        val buttonKayiyOl: Button = binding.ButtonKayTOl

        binding.viewModel = signInSignUpViewModel
        binding.lifecycleOwner = viewLifecycleOwner

        buttonGirisEmail.setOnClickListener {
            val action =
                EmailFragmentDirections.actionEmailFragmentToSignInPasswordFragment(binding.eTextEMail.text.toString())
            findNavController().navigate(action)
        }
        buttonKayiyOl.setOnClickListener {
            val action =
                EmailFragmentDirections.actionEmailFragmentToSignUpFragment(binding.eTextEMail.text.toString())
            findNavController().navigate(action)
        }



        return binding.root
    }


}