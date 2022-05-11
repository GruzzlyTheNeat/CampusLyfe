package com.example.campuslyfe.fragment.etkinlikler

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentEtkinliklerBinding
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class EtkinliklerFragment : Fragment(), EtkinliklerAdapter.OnEtkinlikClickListener {
    private lateinit var etkinlikList : ArrayList<Etkinlik>
    private lateinit var contextEtkinlik : Context
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEtkinliklerBinding.inflate(inflater, container, false).apply {
            contextEtkinlik = requireContext()
            lifecycleOwner = viewLifecycleOwner

            val databaseEtkinlik = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Etkinlikler")
            etkinlikList = arrayListOf<Etkinlik>()
            databaseEtkinlik.addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        for(etkinlikSnapShot in snapshot.children){
                            val etkinlik = etkinlikSnapShot.getValue(Etkinlik::class.java)
                            etkinlikList.add(etkinlik!!)
                        }
                        rvEtkinlikler.adapter =
                            EtkinliklerAdapter(etkinlikList, contextEtkinlik, this@EtkinliklerFragment)

                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    println("hataaa ${error}")
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