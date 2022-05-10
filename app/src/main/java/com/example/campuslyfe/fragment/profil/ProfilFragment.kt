package com.example.campuslyfe.fragment.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentProfilBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class ProfilFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding : FragmentProfilBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding =  FragmentProfilBinding.inflate(inflater,container,false)
        mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid
        val databaseUser = FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/").getReference("Users")

        databaseUser.child(uid.toString()).get().addOnSuccessListener {
            if(it.exists()){
                binding.tvAdProfil.text = it.child("adSoyad").value.toString()
                binding.tvBolumProfil.text = it.child("bolum").value.toString()
                binding.tvBilgiGiris.text = it.child("bilgi").value.toString()

            }
        }
        binding.btProfilGuncelle.setOnClickListener {
            findNavController().navigate(R.id.action_profilFragment_to_profilUpdateFragment)
        }
        return binding.root
    }


}