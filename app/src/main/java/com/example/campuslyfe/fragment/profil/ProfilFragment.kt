package com.example.campuslyfe.fragment.profil

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.createBitmap
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.campuslyfe.R
import com.example.campuslyfe.data.grabImgFromDB
import com.example.campuslyfe.databinding.FragmentProfilBinding
import com.example.campuslyfe.utils.downloadFromURL
import com.example.campuslyfe.utils.placeHolderProgressBar
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fragment_profil.*
import kotlinx.android.synthetic.main.fragment_profil_update.*
import java.io.File

class ProfilFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: FragmentProfilBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentProfilBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid
        val databaseUser =
            FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")
                .getReference("Users")
        if(uid != null){
             val name = uid

            val imagePath = "users/$name"
            val storageRef = FirebaseStorage.getInstance().getReference()
            val imageRef = storageRef.child(imagePath)
            imageRef.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> {
                binding.imgProfil.downloadFromURL(it.toString(), placeHolderProgressBar(requireContext()))

            })
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