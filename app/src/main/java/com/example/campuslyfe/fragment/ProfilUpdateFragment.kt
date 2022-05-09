package com.example.campuslyfe.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentProfilUpdateBinding
import com.example.campuslyfe.databinding.FragmentSignUpBinding
import com.example.campuslyfe.fragment.club.ClubFragmentDirections
import com.example.campuslyfe.fragment.signIn.EmailFragmentDirections
import com.example.campuslyfe.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase
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
        //var database = FirebaseDatabase.getInstance().reference

        binding.ButtonKaydet.setOnClickListener {
            val adSoyad = binding.etAdSoyad.text.toString().trim()
            val bolum = binding.etBolum.text.toString().trim()
            val bilgi = binding.etBilgi.text.toString().trim()
            val user = User(adSoyad, bolum, bilgi)

            println("uid ${uid}")

            if (uid != null) {
//                  databaseReference.child(uid).setValue(user)
                val action =
                    ProfilUpdateFragmentDirections.actionProfilUpdateFragmentToProfilFragment(
                        user
                    )
                findNavController().navigate(action)

                sendToDB().sendUser(user,uid.toString())
//                databaseReference.child(uid).setValue(user).addOnSuccessListener {
//                    println("user.adSoyad ${user.adSoyad}")
//                    val action =
//                        ProfilUpdateFragmentDirections.actionProfilUpdateFragmentToProfilFragment(
//                            user
//                        )
//                    findNavController().navigate(action)
//
//
//                    //findNavController().navigate(R.id.action_profilUpdateFragment_to_profilFragment)
//
//                }.addOnFailureListener {
//
//                    println(it)
//                }


            }
        }



        return binding.root
    }

//    private fun uploadProfilePicture() {
//        imageUri = Uri.parse()
//    }


}