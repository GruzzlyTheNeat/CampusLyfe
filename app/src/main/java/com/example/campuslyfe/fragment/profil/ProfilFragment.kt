package com.example.campuslyfe.fragment.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentProfilBinding
import com.example.campuslyfe.utils.downloadFromURL
import com.example.campuslyfe.utils.placeHolderProgressBar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class ProfilFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentProfilBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid
        val databaseUser =
            FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Users")

        if (uid != null) {
            val imagePath = "users/$uid"
            val storageRef = FirebaseStorage.getInstance().reference
            val imageRef = storageRef.child(imagePath)
            imageRef.downloadUrl.addOnSuccessListener {
                binding.imgProfil.downloadFromURL(
                    it.toString(),
                    placeHolderProgressBar(requireContext())
                )

            }
            imageRef.downloadUrl.addOnFailureListener {
                binding.imgProfil.setImageResource(R.drawable.ic_baseline_person_24)
            }
        }

        databaseUser.child(uid.toString()).get().addOnSuccessListener {
            if (it.exists()) {
                binding.tvAdProfil.text = it.child("adSoyad").value.toString()
                binding.tvBolumProfil.text = it.child("bolum").value.toString()
                binding.tvBilgiGiris.text = it.child("bilgi").value.toString()
            }
        }

        return binding.root
    }


}