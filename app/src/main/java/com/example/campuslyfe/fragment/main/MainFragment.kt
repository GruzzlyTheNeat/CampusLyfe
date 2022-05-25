package com.example.campuslyfe.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentMainBinding.inflate(inflater, container, false).apply {

            val uid = FirebaseAuth.getInstance().currentUser?.uid

            if (
                uid.equals("wMLfENqBlkaLnotsKx0vCDHPpPO2") ||
                uid.equals("s8HhtmBCsuWq5yVyyWFkBydgg9F3") ||
                uid.equals("tqvhsEY7DZTFCJ7dXGVaz4NkJyV2")
            ) {
                linearLayoutKontrol.visibility = View.VISIBLE
            }

            cardViewHarita.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToHaritaFragment())
            }
            cardViewEtkinlik.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToEtkinliklerFragment())
            }
            cardViewTopluluk.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToClubFragment())
            }
            cardViewYemekhane.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToFoodFragment())
            }
            cardViewAyar.setOnClickListener {
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToAdminKontrolFragment())
            }
        }.root
    }


}