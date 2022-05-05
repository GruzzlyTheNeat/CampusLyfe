package com.example.campuslyfe.fragment.club

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentClubBinding
import com.example.campuslyfe.databinding.FragmentClubDetayBinding


class ClubDetayFragment : Fragment() {
    private lateinit var binding : FragmentClubDetayBinding

    private val navArgs by navArgs<ClubDetayFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentClubDetayBinding.inflate(inflater,container,false)
        binding.clubName.text = navArgs.club.name
        binding.ClubPoster.setImageResource(navArgs.club.vektor)
        binding.ClubAciklama.text = navArgs.club.discription
        binding.ClubAdress.text = navArgs.club.address
        binding.ClubIletisimBilgileri.text = navArgs.club.contactInformation

        return binding.root
    }


}