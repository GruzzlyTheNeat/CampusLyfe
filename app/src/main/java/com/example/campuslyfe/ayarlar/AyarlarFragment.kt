package com.example.campuslyfe.ayarlar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.activity.SignInActivity
import com.example.campuslyfe.databinding.FragmentAyarlarBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class AyarlarFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentAyarlarBinding.inflate(inflater, container, false).apply {

            cardViewProfilGuncelle.setOnClickListener {
                findNavController().navigate(
                    AyarlarFragmentDirections.actionAyarlarFragmentToProfilUpdateFragment()
                )
            }
            cardViewCikisYap.setOnClickListener {
                Firebase.auth.signOut()
                startActivity(Intent(requireContext(), SignInActivity::class.java))
                activity?.finish()
            }
            cardViewProgramTanitimi.setOnClickListener {

            }
            cardViewSifreDegistir.setOnClickListener {
                findNavController().navigate(
                    AyarlarFragmentDirections.actionAyarlarFragmentToSifreDegistirFragment()
                )
            }
        }.root
    }



}