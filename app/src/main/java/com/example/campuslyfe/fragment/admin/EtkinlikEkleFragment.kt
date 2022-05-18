package com.example.campuslyfe.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentEtkinlikEkleBinding
import com.example.campuslyfe.databinding.FragmentToplulukEkleBinding
import com.example.campuslyfe.fragment.etkinlikler.EtkinliklerAdapter
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.android.gms.maps.model.LatLng
import org.koin.androidx.viewmodel.ext.android.viewModel


class EtkinlikEkleFragment : Fragment(), MarkerLocationPickUpFragment.EtkinlikLocationSelectedListener{


    private val etkinlikEkleViewModel by viewModel<EtkinlikEkleViewModel>()
    private lateinit var binaList: ArrayList<Bina>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentEtkinlikEkleBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = etkinlikEkleViewModel
            lifecycleOwner = viewLifecycleOwner

            buttonEtkinlikYeriSeciniz.setOnClickListener {
                MarkerLocationPickUpFragment(etkinlikListener = this@EtkinlikEkleFragment).show(
                    childFragmentManager,
                    MarkerLocationPickUpFragment::class.java.name
                )
           }


            buttonEtkinlikKaydet.setOnClickListener {
                val ad = etkinlikEkleViewModel.etkinlikAdi.value!!.trim()
                val aciklama = etkinlikEkleViewModel.etkinlikAciklama.value!!.trim()
                val binaAd = etkinlikEkleViewModel.binaAdi.value!!.trim()
                val iletisimBilgileri = etkinlikEkleViewModel.iletisimBilgileri.value!!.trim()
                val lat = etkinlikEkleViewModel.lat.value!!
                val lng = etkinlikEkleViewModel.lng.value!!
                val etkinlik =
                    Etkinlik(
                        ad,
                        aciklama,
                        binaAd,
                        iletisimBilgileri,
                        1,
                        lat,
                        lng
                    )
                sendToDB().sendEtkinlik(etkinlik)

                val databaseBinalar =
                    FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")
                        .getReference("Binalar")
                binaList = arrayListOf<Bina>()

                databaseBinalar.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            binaList.clear()
                            for (dss in snapshot.children) {
                                val bina = dss.getValue(Bina::class.java)
                                binaList.add(bina!!)
                            }
                            for (b in binaList) {
                                if (binaAd.equals(b.binaAd)) {
                                    b.etkinliklerList?.add(etkinlik)
                                    sendToDB().sendBina(b)


                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })

                findNavController().navigate(R.id.action_etkinlikEkleFragment_to_etkinliklerFragment)
            }
        }
            return binding.root
        }




        override fun onEtkinlikLocationSelected(latLng: LatLng) {
            etkinlikEkleViewModel.lat.postValue(latLng.latitude)
            etkinlikEkleViewModel.lng.postValue(latLng.longitude)
        }
    }