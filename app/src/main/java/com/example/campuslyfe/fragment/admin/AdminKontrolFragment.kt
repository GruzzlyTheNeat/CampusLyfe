package com.example.campuslyfe.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.databinding.FragmentAdminKontrolBinding

class AdminKontrolFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentAdminKontrolBinding.inflate(inflater, container, false).apply {

            cardViewToplulukEkle.setOnClickListener {
                findNavController().navigate(
                    AdminKontrolFragmentDirections.actionAdminKontrolFragmentToToplulukEkleFragment()
                )
            }

            cardViewEtkinlikEkle.setOnClickListener {
                findNavController().navigate(
                    AdminKontrolFragmentDirections.actionAdminKontrolFragmentToEtkinlikEkleFragment()
                )
            }

            cardViewYemekhaneDuzenle.setOnClickListener {
                findNavController().navigate(
                    AdminKontrolFragmentDirections.actionAdminKontrolFragmentToYemekHaneDuzenleFragment()
                )
            }
            cardViewEtkinlikSil.setOnClickListener {
                findNavController().navigate(
                    AdminKontrolFragmentDirections.actionAdminKontrolFragmentToEtkinlikSilFragment()
                )
            }
        }.root

    }


}