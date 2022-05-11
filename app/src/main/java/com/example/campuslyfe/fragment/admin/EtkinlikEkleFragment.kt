package com.example.campuslyfe.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentEtkinlikEkleBinding
import com.example.campuslyfe.databinding.FragmentToplulukEkleBinding
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik

class EtkinlikEkleFragment : Fragment() {


    private lateinit var binding : FragmentEtkinlikEkleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentEtkinlikEkleBinding.inflate(inflater,container,false)

        binding.ButtonEtkinlikKaydet.setOnClickListener {
            val ad = binding.etEtkinlikAd.text.toString().trim()
            val aciklama = binding.etEtkinlikAciklama.text.toString().trim()
            val binaAd = binding.etEtkinlikBinaAd.text.toString().trim()
            val iletisimBilgileri = binding.etIletisimBilgileriEtkinlik.text.toString().trim()
            val lat = binding.etEtkinlikLat.text.toString().trim()
            val lng = binding.etEtkinlikLng.text.toString().trim()
            val etkinlik = Etkinlik(ad,aciklama,binaAd,iletisimBilgileri,1, lat.toDouble(),lng.toDouble())
            sendToDB().sendEtkinlik(etkinlik)

            findNavController().navigate(R.id.action_etkinlikEkleFragment_to_etkinliklerFragment)

        }





        return binding.root
    }


}