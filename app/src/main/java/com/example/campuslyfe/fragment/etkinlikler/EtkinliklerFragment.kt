package com.example.campuslyfe.fragment.etkinlikler

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.databinding.FragmentEtkinliklerBinding
import com.example.campuslyfe.model.Etkinlik
import com.example.campuslyfe.utils.getDatabaseInstance
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class EtkinliklerFragment : Fragment(), EtkinliklerAdapter.OnEtkinlikClickListener {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEtkinliklerBinding.inflate(inflater, container, false).apply {

            lifecycleOwner = viewLifecycleOwner

            val databaseEtkinlik = getDatabaseInstance()
                .getReference("Etkinlikler")
            val etkinlikList = arrayListOf<Etkinlik>()
            databaseEtkinlik.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (etkinlikSnapShot in snapshot.children) {
                            val etkinlik = etkinlikSnapShot.getValue(Etkinlik::class.java)
                            etkinlikList.add(etkinlik!!)
                        }
                        rvEtkinlikler.adapter =
                            EtkinliklerAdapter(
                                etkinlikList,
                                requireContext(),
                                this@EtkinliklerFragment
                            )

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    println("hataaa $error")
                }

            })


        }.root
    }

    override fun onEtkinlikClick(etkinlik: Etkinlik) {
        findNavController().navigate(
            EtkinliklerFragmentDirections.actionEtkinliklerFragmentToEtkinlikDetayFragment(
                etkinlik
            )
        )
    }
}