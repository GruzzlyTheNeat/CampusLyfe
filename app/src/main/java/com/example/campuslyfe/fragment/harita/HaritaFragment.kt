package com.example.campuslyfe.fragment.harita

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentHaritaBinding
import com.example.campuslyfe.fragment.etkinlikler.EtkinliklerAdapter
import com.example.campuslyfe.fragment.etkinlikler.EtkinliklerFragmentDirections
import com.example.campuslyfe.model.Club
import com.example.campuslyfe.model.Etkinlik

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.custom_pop_up.*
import java.lang.Exception
import java.util.*

class HaritaFragment : Fragment(), EtkinlikPopUpRwAdapter.OnPopUpEtkinlikClickListener,ClubPopUpRwAdapter.OnClubPopUpListener {
    private lateinit var binding: FragmentHaritaBinding
    private lateinit var myDialog : Dialog
    private val callback = OnMapReadyCallback { googleMap ->

        val ege = LatLng(38.451918, 27.228451)
        googleMap.addMarker(MarkerOptions().position(ege).title("Ege Üniversitesi Merkezi"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ege, 15F))
        googleMap.setOnMapClickListener {
            val geocoder = Geocoder(context, Locale.getDefault())
            try {
                var adres = ""
                val adress_list = geocoder.getFromLocation(it.latitude, it.longitude, 1)
                if (adress_list.size > 0) {
                    val etkinliklerDummyData = listOf(
                        Etkinlik(
                            "konser monser",
                            "emre aydın konseri",
                            "şelalenin arkası",
                            "054635352523",
                            R.drawable.ic_launcher_background
                        ),
                        Etkinlik(
                            "rektörle maç",
                            "türkiye izlanda maçı",
                            "şelalenin önü",
                            "05055555555",
                            R.drawable.ic_launcher_background
                        ),
                        Etkinlik(
                            "rektörle iftar",
                            "açık büfe",
                            "şelale",
                            "0302402532",
                            R.drawable.ic_launcher_background
                        )
                    )
                    val clubPopUpList = listOf(
                        Club(R.drawable.ic_baseline_apps_24, "Kulüp1","Açıklama1" , "Adres1","Contact1","Müze"),
                        Club(R.drawable.ic_baseline_apps_24, "Kulüp2","Açıklama2" , "Adres2","Contact2","Müze"),
                        Club(R.drawable.ic_baseline_apps_24, "Kulüp3","Açıklama3" , "Adres3","Contact3","Müze"),
//                        Club(R.drawable.ic_baseline_apps_24, "Kulüp1","Açıklama1" , "Adres1","Contact1","Müze"),
//                        Club(R.drawable.ic_baseline_apps_24, "Kulüp2","Açıklama2" , "Adres2","Contact2","Müze"),
//                        Club(R.drawable.ic_baseline_apps_24, "Kulüp3","Açıklama3" , "Adres3","Contact3","Müze")





                    )
                    myDialog = Dialog(requireContext())
                    myDialog.setContentView(R.layout.custom_pop_up)
                    var adapterEtkinlikPopUp = EtkinlikPopUpRwAdapter(etkinliklerDummyData, requireContext(), this@HaritaFragment)
                    var recyclerViewPopUpEtkinlik = myDialog.recyclerViewEtkinlikPopUp
                    recyclerViewPopUpEtkinlik.adapter = adapterEtkinlikPopUp
                    recyclerViewPopUpEtkinlik.layoutManager = LinearLayoutManager(requireContext())
                    var adapterClubPopUp = ClubPopUpRwAdapter(clubPopUpList,requireContext(),this@HaritaFragment)
                    var recyclerViewPopUpClub = myDialog.recyclerViewClubPopUp
                    recyclerViewPopUpClub.adapter = adapterClubPopUp
                    recyclerViewPopUpClub.layoutManager = LinearLayoutManager(requireContext())
                    myDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    myDialog.show()

                    myDialog.setCancelable(true)

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentHaritaBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }

    override fun onEtkinlikPopUpClick(etkinlik: Etkinlik) {
        findNavController().navigate(
            HaritaFragmentDirections.actionHaritaFragmentToEtkinlikDetayFragment(
                etkinlik
            )
        )
        myDialog.cancel()

    }

    override fun onClubPopUpClick(club: Club) {
        findNavController().navigate(
            HaritaFragmentDirections.actionHaritaFragmentToClubDetayFragment(
                club
            )
        )
        myDialog.cancel()
    }
}