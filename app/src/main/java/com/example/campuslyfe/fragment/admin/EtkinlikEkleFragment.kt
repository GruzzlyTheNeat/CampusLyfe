package com.example.campuslyfe.fragment.admin

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
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
import com.example.campuslyfe.databinding.FragmentEtkinlikEkleBinding
import com.example.campuslyfe.model.Bina
import com.example.campuslyfe.model.Etkinlik
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.fragment_etkinlik_ekle.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class EtkinlikEkleFragment : Fragment(), MarkerLocationPickUpFragment.EtkinlikLocationSelectedListener{


    private val etkinlikEkleViewModel by viewModel<EtkinlikEkleViewModel>()
    private lateinit var binaList: ArrayList<Bina>
    private lateinit var imageUriEtkinlik: Uri


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentEtkinlikEkleBinding.inflate(inflater, container, false)
        binding.apply {
            viewModel = etkinlikEkleViewModel
            lifecycleOwner = viewLifecycleOwner

            buttonEtkinlikYeriSeciniz.setOnClickListener {
                MarkerLocationPickUpFragment(etkinlikListener = this@EtkinlikEkleFragment).show(
                    childFragmentManager,
                    MarkerLocationPickUpFragment::class.java.name
                )
           }
            imageViewAdminEtkinlikEkle.setOnClickListener {
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                    if(PermissionChecker.checkSelfPermission(
                            requireContext(),
                            Manifest.permission.READ_EXTERNAL_STORAGE
                        ) == PermissionChecker.PERMISSION_DENIED){
                        val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                        requestPermissions(permissions, EtkinlikEkleFragment.PERMISION_CODE)

                    }else{
                        pickImageFromGaleryEtkinlikEkle()
                    }
                }
                else{
                    pickImageFromGaleryEtkinlikEkle()
                }
            }


            buttonEtkinlikKaydet.setOnClickListener {
                val ad = etkinlikEkleViewModel.etkinlikAdi.value!!.trim()
                val aciklama = etkinlikEkleViewModel.etkinlikAciklama.value!!.trim()
                val binaAd = etkinlikEkleViewModel.binaAdi.value!!.trim()
                val iletisimBilgileri = etkinlikEkleViewModel.iletisimBilgileri.value!!.trim()
                val lat = etkinlikEkleViewModel.lat.value!!
                val lng = etkinlikEkleViewModel.lng.value!!
                val etkinlik =
                    Etkinlik(
                        ad,
                        aciklama,
                        binaAd,
                        iletisimBilgileri,
                        1,
                        lat,
                        lng
                    )
                sendToDB().sendEtkinlik(etkinlik)
                if(::imageUriEtkinlik.isInitialized){
                    sendImgToDB().uploadImgEtkinlik(imageUriEtkinlik,ad)
                    findNavController().navigate(R.id.action_etkinlikEkleFragment_to_etkinliklerFragment)
                }
                else{
                    Toast.makeText(requireContext(),"Lütfen Resim Seçiniz",Toast.LENGTH_SHORT).show()

                }

                val databaseBinalar =
                    FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")
                        .getReference("Binalar")
                binaList = arrayListOf<Bina>()

                databaseBinalar.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if (snapshot.exists()) {
                            binaList.clear()
                            for (dss in snapshot.children) {
                                val bina = dss.getValue(Bina::class.java)
                                binaList.add(bina!!)
                            }
                            for (b in binaList) {
                                if (binaAd.equals(b.binaAd)) {
                                    b.etkinliklerList?.add(etkinlik)
                                    sendToDB().sendBina(b)


                                }
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })

                findNavController().navigate(R.id.action_etkinlikEkleFragment_to_etkinliklerFragment)
            }
        }
            return binding.root
        }




        override fun onEtkinlikLocationSelected(latLng: LatLng) {
            etkinlikEkleViewModel.lat.postValue(latLng.latitude)
            etkinlikEkleViewModel.lng.postValue(latLng.longitude)
        }

    private fun pickImageFromGaleryEtkinlikEkle() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object{
        private val IMAGE_PICK_CODE = 1000
        private val PERMISION_CODE = 1001

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when(requestCode){
            PERMISION_CODE ->{
                if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    pickImageFromGaleryEtkinlikEkle()
                }
                else{
                    Toast.makeText(requireContext(),"Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            imageUriEtkinlik = data?.data!!
            imageViewAdminEtkinlikEkle.setImageURI(imageUriEtkinlik)

        }
    }
    }