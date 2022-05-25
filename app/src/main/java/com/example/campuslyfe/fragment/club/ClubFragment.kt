package com.example.campuslyfe.fragment.club

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campuslyfe.databinding.FragmentClubBinding
import com.example.campuslyfe.model.Club
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class ClubFragment : Fragment() {

    private lateinit var mClubViewModel: ClubViewModel
    private lateinit var clubList: ArrayList<Club>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentClubBinding.inflate(inflater, container, false)

        val databaseTopluluk =
            FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Topluluklar")
        clubList = arrayListOf()
        databaseTopluluk.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (clubSnapShot in snapshot.children) {
                        val club = clubSnapShot.getValue(Club::class.java)
                        clubList.add(club!!)
                    }

                    binding.recyclerViewClub.adapter = ClubRwAdapter(clubList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        mClubViewModel = ViewModelProvider(this).get(ClubViewModel::class.java)

        return binding.root
    }


}
