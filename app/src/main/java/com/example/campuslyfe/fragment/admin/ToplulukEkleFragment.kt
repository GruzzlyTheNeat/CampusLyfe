package com.example.campuslyfe.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentAdminKontrolBinding
import com.example.campuslyfe.databinding.FragmentToplulukEkleBinding
import com.example.campuslyfe.model.Club
import com.google.android.gms.maps.model.LatLng

class ToplulukEkleFragment : Fragment() {

    private lateinit var binding : FragmentToplulukEkleBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentToplulukEkleBinding.inflate(inflater,container,false)



        binding.ButtonToplulukKaydet.setOnClickListener {
            val ad = binding.etToplulukAd.text.toString().trim()
            val aciklama = binding.etToplulukAciklama.text.toString().trim()
            val binaAd = binding.etToplulukBinaAd.text.toString().trim()
            val iletisimBilgileri = binding.etIletisimBilgileri.text.toString().trim()
            val lat = binding.etToplulukLat.text.toString().trim()
            val lng = binding.etToplulukLng.text.toString().trim()
            val topluluk = Club(1,ad,aciklama,binaAd,iletisimBilgileri, lat.toDouble(),lng.toDouble())
            sendToDB().sendTopluluk(topluluk)
            findNavController().navigate(R.id.action_toplulukEkleFragment_to_clubFragment)
        }

        return binding.root
    }


}