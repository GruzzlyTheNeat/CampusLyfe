package com.example.campuslyfe.fragment.etkinlikler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentEtkinlikKatilimciListBinding
import com.example.campuslyfe.model.Etkinlik
import com.example.campuslyfe.model.User
import com.example.campuslyfe.utils.getDatabaseInstance
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class EtkinlikKatilimciListFragment : Fragment() {

    private lateinit var binding: FragmentEtkinlikKatilimciListBinding
    private val argsEtkinlik by navArgs<EtkinlikKatilimciListFragmentArgs>()
    private lateinit var recyclerViewUser: RecyclerView
    private lateinit var userList : ArrayList<User>
    private lateinit var userRealList : ArrayList<User>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEtkinlikKatilimciListBinding.inflate(inflater, container, false)
        userList = arrayListOf()
        userRealList = arrayListOf()
        recyclerViewUser = binding.recyclerViewUser
        val databaseEtkinlikler =
            getDatabaseInstance()
                .getReference("Etkinlikler")

        databaseEtkinlikler.child(argsEtkinlik.etkinlik.etkinlikAd.toString()).addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                    if(snapshot.exists()){
                        val etkinlikSnapShot = snapshot.getValue(Etkinlik::class.java)

                        userList = etkinlikSnapShot?.etkinlikKatilimciListesi!!

                        userRealList = ArrayList(userList).apply {
                            removeAt(0)
                        }
                        val userAdapter = KatilimciRwAdapter(userRealList)
                        recyclerViewUser.apply {
                            adapter = userAdapter
                            layoutManager = LinearLayoutManager(requireContext())
                        }

                    }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })




        return binding.root
    }



}