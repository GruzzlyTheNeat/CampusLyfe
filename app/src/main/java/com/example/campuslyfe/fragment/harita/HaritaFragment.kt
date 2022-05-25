package com.example.campuslyfe.fragment.harita

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentHaritaBinding
import com.example.campuslyfe.model.Bina
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.gson.Gson

class HaritaFragment : Fragment() {

    private val haritaRepo = HaritaRepository()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentHaritaBinding.inflate(inflater, container, false).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val latLngBounds = LatLngBounds.builder()
        (childFragmentManager.findFragmentById(R.id.map) as? SupportMapFragment)?.getMapAsync { map ->
            haritaRepo.binalar.forEach { bina ->
                map.addMarker(
                    MarkerOptions().icon(createMarker()).anchor(0.5f, 0.5f)
                        .position(LatLng(bina.binalat!!, bina.binalng!!))
                        .title(bina.binaAd)
                )?.apply {
                    tag = bina
                }
                latLngBounds.include(LatLng(bina.binalat, bina.binalng))
            }
            map.moveCamera(CameraUpdateFactory.newLatLngBounds(latLngBounds.build(), 100))
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(requireContext(), R.raw.map_style))
            map.setOnMarkerClickListener {
                HaritaBinaBottomSheetFragment().apply {
                    arguments =
                        bundleOf(HaritaBinaBottomSheetFragment.PARAM_BINA to Gson().toJson(it.tag as Bina))
                    dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                }.show(childFragmentManager, HaritaBinaBottomSheetFragment::class.simpleName)
                false
            }
        }
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