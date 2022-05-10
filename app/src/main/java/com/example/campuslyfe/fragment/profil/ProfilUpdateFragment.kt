package com.example.campuslyfe.fragment.profil

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentProfilUpdateBinding
import com.example.campuslyfe.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference

class ProfilUpdateFragment : Fragment() {
    private lateinit var binding: FragmentProfilUpdateBinding
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
//    private lateinit var sendToDB: sendToDB
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentProfilUpdateBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid


        binding.ButtonKaydet.setOnClickListener {
            val adSoyad = binding.etAdSoyad.text.toString().trim()
            val bolum = binding.etBolum.text.toString().trim()
            val bilgi = binding.etBilgi.text.toString().trim()
            val user = User(adSoyad, bolum, bilgi)

            println("uid ${uid}")

            if (uid != null) {
                findNavController().navigate(R.id.action_profilUpdateFragment_to_profilFragment)

                sendToDB().sendUser(user,uid.toString())


            }
        }



        return binding.root
    }



}