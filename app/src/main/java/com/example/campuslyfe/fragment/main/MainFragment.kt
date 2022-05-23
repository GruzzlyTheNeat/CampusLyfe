package com.example.campuslyfe.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentMainBinding
import com.google.firebase.auth.FirebaseAuth


class MainFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth

    private lateinit var binding : FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater,container,false)
        mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid
        val buttonHarita : CardView = binding.cardViewHarita
        val buttonClub : CardView = binding.cardViewTopluluk
        val buttonYemek : CardView = binding.cardViewYemekhane
        val buttonKontrol : CardView = binding.cardViewAyar

        if(uid.equals("wMLfENqBlkaLnotsKx0vCDHPpPO2") || uid.equals("s8HhtmBCsuWq5yVyyWFkBydgg9F3" ) || uid.equals("tqvhsEY7DZTFCJ7dXGVaz4NkJyV2" )){
            binding.linearLayoutKontrol.visibility = View.VISIBLE
        }

        buttonHarita.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_haritaFragment)

        }
        binding.cardViewEtkinlik.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToEtkinliklerFragment())
        }

        buttonClub.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_clubFragment)
        }

        buttonYemek.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_foodFragment)
        }
        buttonKontrol.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_adminKontrolFragment)
        }


        return binding.root
    }


}