package com.example.campuslyfe.fragment.club

import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentClubBinding
import com.example.campuslyfe.databinding.FragmentClubDetayBinding
import com.example.campuslyfe.utils.downloadFromURL
import com.example.campuslyfe.utils.placeHolderProgressBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.callbackFlow


class ClubDetayFragment : Fragment(){
    private lateinit var binding : FragmentClubDetayBinding
    private val navArgs by navArgs<ClubDetayFragmentArgs>()


    private val callback = OnMapReadyCallback { googleMap ->

        val latlng = LatLng(navArgs.club.lat!!,navArgs.club.lng!!)
        googleMap.addMarker(MarkerOptions().icon(createMarker()).anchor(0.5f,0.5f).position(latlng).title(navArgs.club.binaAd))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng,15F))

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentClubDetayBinding.inflate(inflater,container,false)

        binding.clubName.text = navArgs.club.name
        //binding.ClubPoster.setImageResource(navArgs.club.vektor.toString().toInt())
        binding.ClubAciklama.text = navArgs.club.discription
        binding.ClubAdress.text = navArgs.club.binaAd
        binding.ClubIletisimBilgileri.text = navArgs.club.contactInformation

        if(navArgs.club.name != null){
            val name = navArgs.club.name

            val imagePath = "topluluklar/$name"
            val storageRef = FirebaseStorage.getInstance().getReference()
            val imageRef = storageRef.child(imagePath)
            imageRef.downloadUrl.addOnSuccessListener(OnSuccessListener<Uri> {
                binding.clubPoster.downloadFromURL(it.toString(), placeHolderProgressBar(requireContext()))

            })
            imageRef.downloadUrl.addOnFailureListener {
                binding.clubPoster.setImageResource(R.drawable.ic_launcher_foreground)
            }



        }


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.clubMapDetails) as SupportMapFragment
        mapFragment.getMapAsync(callback)


    }
    private fun createMarker(): BitmapDescriptor {
        val drawable = ContextCompat.getDrawable(requireContext(), R.drawable.ic_ege)!!
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        drawable.draw(canvas)

        return BitmapDescriptorFactory.fromBitmap(bitmap)
    }



}