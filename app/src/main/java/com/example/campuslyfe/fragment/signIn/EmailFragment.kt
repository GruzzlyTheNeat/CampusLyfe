package com.example.campuslyfe.fragment.signIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentEmailBinding
import com.example.campuslyfe.databinding.FragmentMainBinding

class EmailFragment : Fragment() {

    private lateinit var binding : FragmentEmailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentEmailBinding.inflate(inflater,container,false)
        val buttonGirisEmail : Button = binding.ButtondevamEtGiris
//        val eMail : String = binding.eTextEMail.text.toString()

        buttonGirisEmail.setOnClickListener {
            val action = EmailFragmentDirections.actionEmailFragmentToSignInPasswordFragment(binding.eTextEMail.text.toString())

            Navigation.findNavController(binding.root).navigate(action)
        }

//        buttonHarita.setOnClickListener {
//            findNavController().navigate(R.id.action_mainFragment_to_signInActivity)
//
//        }
        return binding.root
    }


}