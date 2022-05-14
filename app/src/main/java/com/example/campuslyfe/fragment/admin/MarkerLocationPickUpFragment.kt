package com.example.campuslyfe.fragment.admin

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentMarkerLocationPickupBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson

class MarkerLocationPickUpFragment(
    private val etkinlikListener: EtkinlikLocationSelectedListener? = null,
    private val toplulukkListener: ToplulukLocationSelectedListener? = null
) : DialogFragment() {

    private lateinit var map: SupportMapFragment

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentMarkerLocationPickupBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner

            map = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment

            btnKonumSec.setOnClickListener {
                map.getMapAsync {
                    it.cameraPosition.target.apply {
                        if (parentFragment is EtkinlikEkleFragment)
                            etkinlikListener?.onEtkinlikLocationSelected(this)
                        else
                            toplulukkListener?.onToplulukLocationSelected(this)
                    }
                }
                dismiss()
            }
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val latLng = Gson().fromJson(arguments?.getString(PARAM_LAT_LNG), LatLng::class.java)

        map.getMapAsync { map ->
            map.moveCamera(
                CameraUpdateFactory.newCameraPosition(
                    CameraPosition.fromLatLngZoom(
                        LatLng(latLng?.latitude ?: 38.45787998795536, latLng?.longitude ?:27.213112255208536),
                        15f
                    )
                )
            )
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style))
            map.addMarker(MarkerOptions().position(map.cameraPosition.target))
            map.setOnCameraMoveListener {
                map.clear()
                map.addMarker(MarkerOptions().position(map.cameraPosition.target))
            }
        }
    }

    interface EtkinlikLocationSelectedListener {
        fun onEtkinlikLocationSelected(latLng: LatLng)
    }

    interface ToplulukLocationSelectedListener {
        fun onToplulukLocationSelected(latLng: LatLng)
    }

    companion object {
        const val PARAM_LAT_LNG = "param_lat_lng"
    }
}