package com.example.campuslyfe.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentClubBinding
import com.example.campuslyfe.databinding.FragmentProfilBinding
import com.example.campuslyfe.fragment.signIn.SignInPasswordFragmentArgs
import com.google.firebase.auth.FirebaseAuth

class ProfilFragment : Fragment() {
    private val args by navArgs<ProfilFragmentArgs>()

    private lateinit var binding : FragmentProfilBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =  FragmentProfilBinding.inflate(inflater,container,false)



        println(args.user?.adSoyad)
        if(args.user?.adSoyad!=null){
           binding.tvAdProfil.text = args.user?.adSoyad
        }
        if(args.user?.bolum!=null){
            binding.tvBolumProfil.text = args.user?.bolum
        }
        if(args.user?.bilgi!=null){
            binding.tvBilgiGiris.text = args.user?.bilgi
        }


        binding.btProfilGuncelle.setOnClickListener {
            findNavController().navigate(R.id.action_profilFragment_to_profilUpdateFragment)
        }
        return binding.root
    }


}