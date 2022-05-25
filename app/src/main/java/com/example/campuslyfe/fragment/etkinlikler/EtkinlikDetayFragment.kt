package com.example.campuslyfe.fragment.etkinlikler

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentEtkinlikDetayBinding
import com.example.campuslyfe.utils.downloadFromURL
import com.example.campuslyfe.utils.placeHolderProgressBar
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.storage.FirebaseStorage

class EtkinlikDetayFragment : Fragment() {

    private val navArgs by navArgs<EtkinlikDetayFragmentArgs>()
    private val callback = OnMapReadyCallback { googleMap ->

        val latlng = LatLng(navArgs.etkinlik.etkinlikLat!!, navArgs.etkinlik.etkinlikLng!!)
        googleMap.addMarker(
            MarkerOptions().icon(createMarker()).anchor(0.5f, 0.5f).position(latlng)
                .title(navArgs.etkinlik.etkinlikAdres)
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 15F))

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEtkinlikDetayBinding.inflate(inflater, container, false).apply {
            etkinlik = navArgs.etkinlik
            lifecycleOwner = viewLifecycleOwner
            if (etkinlik?.etkinlikAd != null) {
                val name = etkinlik?.etkinlikAd

                val imagePath = "etkinlikler/$name"
                val storageRef = FirebaseStorage.getInstance().reference
                val imageRef = storageRef.child(imagePath)
                imageRef.downloadUrl.addOnSuccessListener {
                    etkinlikPoster.downloadFromURL(
                        it.toString(),
                        placeHolderProgressBar(requireContext())
                    )

                }
                imageRef.downloadUrl.addOnFailureListener {
                    etkinlikPoster.setImageResource(R.drawable.ic_launcher_foreground)
                }

            }

        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.etkinlikMapDetail) as SupportMapFragment
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