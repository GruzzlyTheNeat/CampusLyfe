package com.example.campuslyfe.ayarlar

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.PermissionChecker
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendImgToDB
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentProfilUpdateBinding
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik
import com.example.campuslyfe.model.User
import com.example.campuslyfe.utils.showToast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.fragment_profil_update.*

class ProfilUpdateFragment : Fragment() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var databaseReference: DatabaseReference
    private lateinit var storageReference: StorageReference
    private lateinit var imageUri: Uri
    private lateinit var etkinlikList : ArrayList<Etkinlik>
    private lateinit var toplulukList : ArrayList<Club>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentProfilUpdateBinding.inflate(inflater, container, false)
        mAuth = FirebaseAuth.getInstance()
        val uid = mAuth.currentUser?.uid
        binding.imgProfilUpdate.setOnClickListener {
            if (checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PermissionChecker.PERMISSION_DENIED
            ) {
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                requestPermissions(permissions, PERMISION_CODE)

            } else {
                pickImageFromGalery()
            }
        }
        val defaultEtkinlik  = Etkinlik("etkinlik Ad","etkinlik acıklama","etkinlik adres","iletişim bilgileri",2.6,4.6,
            arrayListOf<User>())
        val defaultTopluluk = Club("club ad","club discription","binaAd","contactInformation",5.2,6.4,
            arrayListOf<User>())
        etkinlikList = arrayListOf()
        toplulukList = arrayListOf()
        etkinlikList.add(defaultEtkinlik)
        toplulukList.add(defaultTopluluk)
            binding.ButtonKaydet.setOnClickListener {
            val adSoyad = binding.etAdSoyad.text.toString().trim()
            val bolum = binding.etBolum.text.toString().trim()
            val bilgi = binding.etBilgi.text.toString().trim()
            val user = User(uid.toString(),adSoyad, bolum, bilgi,etkinlikList,toplulukList)

            if (uid != null) {

                if(::imageUri.isInitialized){
                    sendImgToDB().uploadImgUser(imageUri, uid.toString())
                    findNavController().navigate(R.id.action_profilUpdateFragment_to_profilFragment)

                }
                else{
                    showToast("Lütfen resim seçiniz")
                }

                sendToDB().sendUser(user, uid.toString())
            }
        }

        return binding.root
    }

    private fun pickImageFromGalery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private const val IMAGE_PICK_CODE = 1000
        private const val PERMISION_CODE = 1001

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGalery()
                } else {
                    showToast("Permission denied")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageUri = data?.data!!
            imgProfilUpdate.setImageURI(imageUri)
        }
    }

}