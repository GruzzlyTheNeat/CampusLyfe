package com.example.campuslyfe.ayarlar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentAyarlarBinding
import com.example.campuslyfe.databinding.FragmentMainBinding
import com.example.campuslyfe.databinding.FragmentProfilBinding

class AyarlarFragment : Fragment() {
    private lateinit var binding : FragmentAyarlarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentAyarlarBinding.inflate(inflater, container, false)


        binding.cardViewProfilGuncelle.setOnClickListener {
            findNavController().navigate(R.id.action_ayarlarFragment_to_profilUpdateFragment)

        }
        binding.cardViewCikisYap.setOnClickListener {


        }
        binding.cardViewProgramTanitimi.setOnClickListener {

        }
        binding.cardViewSifreDegistir.setOnClickListener {
            findNavController().navigate(R.id.action_ayarlarFragment_to_sifreDegistirFragment)
        }

        return binding.root
    }



}