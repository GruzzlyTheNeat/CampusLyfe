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


class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater,container,false)
        val buttonHarita : CardView = binding.cardViewHarita
        val buttonFood : CardView = binding.cardViewYemekhane

        buttonHarita.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_signInActivity)

        }
        buttonFood.setOnClickListener {
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToFoodFragment())

        }
        return binding.root
    }


}