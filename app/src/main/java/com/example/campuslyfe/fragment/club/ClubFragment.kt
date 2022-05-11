package com.example.campuslyfe.fragment.club

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentClubBinding
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase


class ClubFragment : Fragment() {

    private lateinit var binding : FragmentClubBinding
    private lateinit var mClubViewModel : ClubViewModel
    private lateinit var layoutManagerClub: LinearLayoutManager
    private lateinit var clubList : ArrayList<Club>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentClubBinding.inflate(inflater,container,false)
        layoutManagerClub = LinearLayoutManager(requireContext())
//        val clubList = listOf(
//            Club(R.drawable.ic_baseline_apps_24, "Kulüp1","Açıklama1" , "Adres1","Contact1",
//                LatLng(3.5,6.0)
//            ),
//            Club(R.drawable.ic_baseline_apps_24, "Kulüp2","Açıklama2" , "Adres2","Contact2",LatLng(3.5,6.0)),
//            Club(R.drawable.ic_baseline_apps_24, "Kulüp3","Açıklama3" , "Adres3","Contact3",LatLng(3.5,6.0))
//
//
//
//
//        )

        val databaseTopluluk = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Topluluklar")
        clubList = arrayListOf<Club>()
        databaseTopluluk.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for(clubSnapShot in snapshot.children){

                        val club = clubSnapShot.getValue(Club::class.java)
                        clubList.add(club!!)
                    }
                    var adapterClub = ClubRwAdapter(clubList)
                    var recyclerViewClub = binding.recyclerViewClub
                    recyclerViewClub.adapter = adapterClub
                    recyclerViewClub.layoutManager = layoutManagerClub
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        mClubViewModel = ViewModelProvider(this).get(ClubViewModel::class.java)




        return binding.root
    }


    }
