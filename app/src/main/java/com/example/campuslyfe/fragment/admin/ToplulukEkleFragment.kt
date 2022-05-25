package com.example.campuslyfe.fragment.admin

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
import android.widget.Toast
import androidx.core.content.PermissionChecker
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendImgToDB
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentToplulukEkleBinding
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Club
import com.google.android.gms.maps.model.LatLng
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.fragment_topluluk_ekle.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ToplulukEkleFragment : Fragment(),
    MarkerLocationPickUpFragment.ToplulukLocationSelectedListener {

    private val toplulukEkleViewModel by viewModel<ToplulukEkleViewModel>()
    private var binaList = ArrayList<Bina>()
    private lateinit var imageUriTopluluk: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentToplulukEkleBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = toplulukEkleViewModel

            imageViewAdminToplulukEkle.setOnClickListener {
                if (PermissionChecker.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    ) == PermissionChecker.PERMISSION_DENIED
                ) {
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISION_CODE)

                } else {
                    pickImageFromGaleryToplulukEkle()
                }
            }


            buttonToplulukYeriSeciniz.setOnClickListener {
                MarkerLocationPickUpFragment(toplulukkListener = this@ToplulukEkleFragment).show(
                    childFragmentManager,
                    MarkerLocationPickUpFragment::class.java.name
                )
            }

            buttonToplulukKaydet.setOnClickListener {
                val ad = toplulukEkleViewModel.toplulukAdi.value!!.trim()
                val aciklama = toplulukEkleViewModel.toplulukAciklama.value!!.trim()
                val binaAd = toplulukEkleViewModel.binaAdi.value!!.trim()
                val iletisimBilgileri = toplulukEkleViewModel.iletisimBilgileri.value!!.trim()
                val lat = toplulukEkleViewModel.lat.value!!
                val lng = toplulukEkleViewModel.lng.value!!
                val topluluk =
                    Club(
                        1,
                        ad,
                        aciklama,
                        binaAd,
                        iletisimBilgileri,
                        lat,
                        lng
                    )
                sendToDB().sendTopluluk(topluluk)
                if (::imageUriTopluluk.isInitialized) {
                    sendImgToDB().uploadImgTopluluk(imageUriTopluluk, ad)
                    findNavController().navigate(R.id.action_toplulukEkleFragment_to_clubFragment)

                } else {
                    Toast.makeText(requireContext(), "Lütfen Resim Seçiniz", Toast.LENGTH_SHORT)
                        .show()
                }


                val databaseBinalar =
                    FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")
                        .getReference("Binalar")
                binaList = arrayListOf()
                databaseBinalar.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            binaList.clear()
                            for (dss in snapshot.children) {
                                val bina = dss.getValue(Bina::class.java)
                                binaList.add(bina!!)
                            }
                            for (b in binaList) {
                                if (binaAd == b.binaAd) {
                                    b.topulukList?.add(topluluk)
                                    sendToDB().sendBina(b)
                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {

                    }

                })

            }
        }

        return binding.root
    }

    override fun onToplulukLocationSelected(latLng: LatLng) {
        toplulukEkleViewModel.lat.postValue(latLng.latitude)
        toplulukEkleViewModel.lng.postValue(latLng.longitude)
    }

    private fun pickImageFromGaleryToplulukEkle() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISION_CODE = 1001

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGaleryToplulukEkle()
                } else {
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imageUriTopluluk = data?.data!!
            imageViewAdminToplulukEkle.setImageURI(imageUriTopluluk)

        }
    }


}