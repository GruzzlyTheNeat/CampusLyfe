package com.example.campuslyfe.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentAdminKontrolBinding
import com.example.campuslyfe.databinding.FragmentMainBinding
import com.example.campuslyfe.databinding.FragmentProfilBinding

class AdminKontrolFragment : Fragment() {

    private lateinit var binding : FragmentAdminKontrolBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAdminKontrolBinding.inflate(inflater,container,false)

        binding.cardViewToplulukEkle.setOnClickListener {
            findNavController().navigate(R.id.action_adminKontrolFragment_to_toplulukEkleFragment)
        }

        binding.cardViewEtkinlikEkle.setOnClickListener {
            findNavController().navigate(R.id.action_adminKontrolFragment_to_etkinlikEkleFragment)
        }



        return binding.root

    }


}