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
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.databinding.FragmentEtkinlikDetayBinding
import com.example.campuslyfe.model.Etkinlik
import com.example.campuslyfe.model.User
import com.example.campuslyfe.utils.downloadFromURL
import com.example.campuslyfe.utils.getDatabaseInstance
import com.example.campuslyfe.utils.placeHolderProgressBar
import com.example.campuslyfe.utils.showToast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.dialog_harita_bina_bottom_sheet.*

class EtkinlikDetayFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private val navArgs by navArgs<EtkinlikDetayFragmentArgs>()
    private lateinit var etkinlikNameList : ArrayList<String>


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
            mAuth = FirebaseAuth.getInstance()

            val uid = mAuth.currentUser?.uid

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
            val databaseUsers =
                getDatabaseInstance()
                    .getReference("Users")
            val databaseEtkinlikler =
                getDatabaseInstance()
                    .getReference("Etkinlikler")
            etkinlikNameList = arrayListOf()

            rgEtkinlikKatilma.setOnCheckedChangeListener { group, checkedId ->
                var radioButton: View = rgEtkinlikKatilma.findViewById(checkedId)
                var index = rgEtkinlikKatilma.indexOfChild(radioButton)
                when (index) {
                    0 -> {
                        showToast("Profil Bilgilerinizin dolu olduğundan eminseniz etkinliğe katıldığınızı bildirebilirsiniz")
                        if (uid != null) {

                            databaseUsers.child(uid.toString())
                                .addListenerForSingleValueEvent(object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        if (snapshot.exists()) {
                                            val user = snapshot.getValue(User::class.java)
                                            val etkinlikName = etkinlik?.etkinlikAd
                                            for (e in user?.katildigiEtkinlikler!!){
                                                etkinlikNameList.add(e.etkinlikAd!!)

                                            }
                                            if(etkinlikNameList.contains(etkinlikName)==false){
                                                user.katildigiEtkinlikler.add(etkinlik!!)
                                                sendToDB().sendUser(user, uid)
                                            }


                                            databaseEtkinlikler.child(etkinlik?.etkinlikAd!!.toString())
                                                .addListenerForSingleValueEvent(object :
                                                    ValueEventListener {
                                                    override fun onDataChange(snapshot: DataSnapshot) {
                                                        if (snapshot.exists()) {
                                                            val etkinlikSnapShot =
                                                                snapshot.getValue((Etkinlik::class.java))
                                                            if(etkinlikSnapShot?.etkinlikKatilimciListesi?.contains(user)==false){
                                                                    etkinlikSnapShot?.etkinlikKatilimciListesi?.add(
                                                                        user!!
                                                                    )
                                                                    sendToDB().sendEtkinlik(
                                                                        etkinlikSnapShot!!
                                                                    )


                                                            }


                                                        }
                                                    }

                                                    override fun onCancelled(error: DatabaseError) {
                                                    }

                                                })


                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                    }

                                })


                        }


                    }

                    1 ->{
                        showToast("Profil Bilgilerinizin dolu olduğundan eminseniz etkinliğe katılmadığınızı bildirebilirsiniz")
                        if(uid!=null){
                            databaseUsers.child(uid.toString()).addListenerForSingleValueEvent(
                                object : ValueEventListener {
                                    override fun onDataChange(snapshot: DataSnapshot) {
                                        if (snapshot.exists()){
                                            val deleteUser = snapshot.getValue(User::class.java)
                                            if(deleteUser?.katildigiEtkinlikler?.contains(etkinlik)==true){
                                                deleteUser?.katildigiEtkinlikler?.remove(etkinlik!!)
                                                sendToDB().sendUser(deleteUser!!,uid)

                                            }
                                            databaseEtkinlikler.child(etkinlik?.etkinlikAd!!.toString()).addListenerForSingleValueEvent(
                                                object : ValueEventListener {
                                                    override fun onDataChange(snapshot: DataSnapshot) {
                                                        val deleteEtkinlik = snapshot.getValue(Etkinlik::class.java)
                                                        if(deleteEtkinlik?.etkinlikKatilimciListesi?.contains(deleteUser)==true){
                                                            deleteEtkinlik?.etkinlikKatilimciListesi?.remove(deleteUser!!)
                                                            sendToDB().sendEtkinlik(deleteEtkinlik!!)
                                                        }

                                                    }

                                                    override fun onCancelled(error: DatabaseError) {
                                                    }

                                                })
                                        }
                                    }

                                    override fun onCancelled(error: DatabaseError) {
                                    }

                                })
                        }


                    }
                }
            }
            etkinlikKatilimcilar.setOnClickListener {
                val action = EtkinlikDetayFragmentDirections.actionEtkinlikDetayFragmentToEtkinlikKatilimciListFragment(etkinlik!!)
                findNavController().navigate(action)
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