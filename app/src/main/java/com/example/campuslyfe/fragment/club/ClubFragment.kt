package com.example.campuslyfe.fragment.club

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentClubBinding
import com.example.campuslyfe.model.Club


class ClubFragment : Fragment() {

    private lateinit var binding : FragmentClubBinding
    private lateinit var mClubViewModel : ClubViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentClubBinding.inflate(inflater,container,false)
        val clubList = listOf(
            Club(R.drawable.ic_baseline_apps_24, "Kulüp1","Açıklama1" , "Adres1","Contact1"),
            Club(R.drawable.ic_baseline_apps_24, "Kulüp2","Açıklama2" , "Adres2","Contact2"),
            Club(R.drawable.ic_baseline_apps_24, "Kulüp3","Açıklama3" , "Adres3","Contact3")




        )
        var adapterClub = ClubRwAdapter(clubList)
        var recyclerViewClub = binding.recyclerViewClub
        recyclerViewClub.adapter = adapterClub
        recyclerViewClub.layoutManager = LinearLayoutManager(requireContext())
        mClubViewModel = ViewModelProvider(this).get(ClubViewModel::class.java)




        return binding.root
    }


    }
