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
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Club
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToplulukEkleFragment : Fragment(),
    MarkerLocationPickUpFragment.ToplulukLocationSelectedListener {

    private val toplulukEkleViewModel by viewModel<ToplulukEkleViewModel>()
    private var binaList = ArrayList<Bina>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentToplulukEkleBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = toplulukEkleViewModel

            buttonToplulukYeriSeciniz.setOnClickListener {
                MarkerLocationPickUpFragment(toplulukkListener = this@ToplulukEkleFragment).show(
                    childFragmentManager,
                    MarkerLocationPickUpFragment::class.java.name
                )
            }

            buttonToplulukKaydet.setOnClickListener {
                val ad = toplulukEkleViewModel.toplulukAdi.value!!.trim()
                val aciklama = toplulukEkleViewModel.toplulukAciklama.value!!.trim()
                val binaAd = toplulukEkleViewModel.binaAdi.value!!.trim()
                val iletisimBilgileri = toplulukEkleViewModel.iletisimBilgileri.value!!.trim()
                val lat = toplulukEkleViewModel.lat.value!!
                val lng = toplulukEkleViewModel.lng.value!!
                val topluluk =
                    Club(
                        1,
                        ad,
                        aciklama,
                        binaAd,
                        iletisimBilgileri,
                        lat,
                        lng
                    )
                sendToDB().sendTopluluk(topluluk)

                val databaseBinalar = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Binalar")
                binaList = arrayListOf<Bina>()
                databaseBinalar.addListenerForSingleValueEvent(object: ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.exists()){
                            binaList.clear()
                            for(dss in snapshot.children){
                                val bina = dss.getValue(Bina::class.java)
                                binaList.add(bina!!)
                            }
                            for(b in binaList){
                                if(binaAd.equals(b.binaAd)){
                                    b.topulukList?.add(topluluk)
                                    sendToDB().sendBina(b)
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })
                findNavController().navigate(R.id.action_toplulukEkleFragment_to_clubFragment)
            }
        }

        return binding.root
    }

    override fun onToplulukLocationSelected(latLng: LatLng) {
        toplulukEkleViewModel.lat.postValue(latLng.latitude)
        toplulukEkleViewModel.lng.postValue(latLng.longitude)
    }


}