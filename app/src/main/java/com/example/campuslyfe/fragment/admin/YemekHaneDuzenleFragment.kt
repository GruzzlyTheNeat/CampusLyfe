package com.example.campuslyfe.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentToplulukEkleBinding
import com.example.campuslyfe.databinding.FragmentYemekhaneDuzenleBinding
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Club
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.koin.androidx.viewmodel.ext.android.viewModel


class YemekHaneDuzenleFragment: Fragment() {
    private val yemekhaneDuzenleViewModel by viewModel<YemekhaneDuzenleViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentToplulukEkleBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = yemekhaneDuzenleViewModel

            buttonToplulukYeriSeciniz.setOnClickListener {
                MarkerLocationPickUpFragment(toplulukkListener = this@ToplulukEkleFragment).show(
                    childFragmentManager,
                    MarkerLocationPickUpFragment::class.java.name
                )
            }
    }
}

