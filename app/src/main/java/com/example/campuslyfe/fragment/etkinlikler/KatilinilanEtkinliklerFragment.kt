package com.example.campuslyfe.fragment.etkinlikler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentKatilimciProfilBinding
import com.example.campuslyfe.databinding.FragmentKatilinilanEtkinliklerBinding
import com.example.campuslyfe.fragment.admin.EtkinlikEkleFragmentDirections
import com.example.campuslyfe.model.Etkinlik

class KatilinilanEtkinliklerFragment : Fragment(), EtkinliklerAdapter.OnEtkinlikClickListener{
    private lateinit var binding: FragmentKatilinilanEtkinliklerBinding
    private val argsUser by navArgs<KatilinilanEtkinliklerFragmentArgs>()
    private lateinit var etkinlikUserList : ArrayList<Etkinlik>
    private lateinit var etkinlikUserRealList : ArrayList<Etkinlik>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKatilinilanEtkinliklerBinding.inflate(inflater, container, false)
        etkinlikUserList = arrayListOf()
        etkinlikUserList = argsUser.userEtkinlik.katildigiEtkinlikler!!
        etkinlikUserRealList = ArrayList(etkinlikUserList).apply {
            removeAt(0)
        }

        binding.recyclerViewUserEtkinlik.adapter =
            EtkinliklerAdapter(
                etkinlikUserRealList,
                requireContext(),
                this@KatilinilanEtkinliklerFragment
            )
        return binding.root
    }

    override fun onEtkinlikClick(etkinlik: Etkinlik?) {
        etkinlik?.let {
            findNavController().navigate(
                KatilinilanEtkinliklerFragmentDirections
                    .actionKatilinilanEtkinliklerFragmentToEtkinlikDetayFragment(it)
            )
        }    }


}