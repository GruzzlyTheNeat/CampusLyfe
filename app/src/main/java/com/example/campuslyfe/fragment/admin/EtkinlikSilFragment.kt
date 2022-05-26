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
import com.example.campuslyfe.databinding.FragmentEtkinlikSilBinding
import com.example.campuslyfe.fragment.etkinlikler.EtkinliklerAdapter
import com.example.campuslyfe.fragment.etkinlikler.EtkinliklerFragmentDirections
import com.example.campuslyfe.fragment.etkinlikler.EtkinliklerViewModel
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Etkinlik
import com.example.campuslyfe.utils.getDatabaseInstance
import com.example.campuslyfe.utils.showToast
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import org.koin.androidx.viewmodel.ext.android.viewModel

class EtkinlikSilFragment : Fragment(), EtkinlikSilRwAdapter.OnEtkinlikSilClickListener {

    private lateinit var binaList: ArrayList<Bina>
    private val etkinlikSilViewModel by viewModel<EtkinlikSilViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentEtkinlikSilBinding.inflate(inflater, container, false).apply {

            lifecycleOwner = viewLifecycleOwner

            etkinlikSilViewModel.etkinlikSilList.observe(viewLifecycleOwner) {
                rvEtkinlikSil.adapter =
                    EtkinlikSilRwAdapter(
                        it,
                        requireContext(),
                        this@EtkinlikSilFragment
                    )
            }

        }.root


        }

    override fun onEtkinlikSilClick(etkinlik: Etkinlik?) {
        etkinlik?.let {etkinlikSil ->
            val database = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Etkinlikler")
            database.child(etkinlikSil.etkinlikAd!!).removeValue().addOnSuccessListener  {
                showToast("Etkinlik Silindi")
                val databaseBinalar =
                    getDatabaseInstance()
                        .getReference("Binalar")
                binaList = arrayListOf()

                databaseBinalar.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            binaList.clear()
                            for (dss in snapshot.children) {
                                val bina = dss.getValue(Bina::class.java)
                                binaList.add(bina!!)
                            }
                            for (b in binaList) {
                                if (etkinlikSil.etkinlikAdres == b.binaAd) {
                                    b.etkinliklerList?.remove(etkinlikSil)
                                    sendToDB().sendBina(b)


                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

            }.addOnFailureListener {
                showToast("Etkinlik Silinemedi")
            }
            val storageRef = FirebaseStorage.getInstance().getReference("etkinlikler/${etkinlikSil.etkinlikAd}")
            storageRef.delete().addOnSuccessListener {
            }.addOnFailureListener {
            }

        }

    }


}