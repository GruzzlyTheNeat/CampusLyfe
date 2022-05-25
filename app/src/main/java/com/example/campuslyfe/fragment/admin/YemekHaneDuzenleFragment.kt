package com.example.campuslyfe.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.Toast
import com.example.campuslyfe.databinding.FragmentYemekhaneDuzenleBinding
import com.example.campuslyfe.model.Yemek
import com.example.campuslyfe.model.Yemekhane
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class YemekHaneDuzenleFragment: Fragment() {
    private lateinit var yemekhaneList: ArrayList<Yemekhane>
    private var haftalik1: Yemekhane? = null
    private var haftalik2: Yemekhane? = null
    private var haftalik3: Yemekhane? = null
    private lateinit var haftalikList: ArrayList<Yemek>
    private val yemekhaneDuzenleViewModel by viewModel<YemekhaneDuzenleViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        var sayiGun = 7
        var sayiYemekhane = 3
        val binding = FragmentYemekhaneDuzenleBinding.inflate(inflater, container, false)
        binding.viewModel = yemekhaneDuzenleViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val yemekhane = createYemekhanePopMenu(binding.etYemekHaneSec)
        val gunler = createGunlerPopMenu(binding.etGunSecimi)
        binding.etYemekHaneSec.setOnClickListener{yemekhane.show()}
        binding.etGunSecimi.setOnClickListener{gunler.show()}
        binding.yemekKaydet.setOnClickListener{
            val databaseYemek =
                FirebaseDatabase.getInstance("https://campuslyfe-b725b-default-rtdb.europe-west1.firebasedatabase.app/")
                    .getReference("Yemekhane")
            yemekhaneList = arrayListOf()
            haftalikList = arrayListOf()

            databaseYemek.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        for (yemekhaneSnapShot in snapshot.children) {
                            val yemekhane = yemekhaneSnapShot.getValue(Yemekhane::class.java)
                            yemekhaneList.add(yemekhane!!)

                        }
                        haftalik1 = yemekhaneList.get(0)
                        haftalik2 = yemekhaneList.get(1)
                        haftalik3 = yemekhaneList.get(2)
                        when (binding.etGunSecimi.text.toString()) {
                            "Pazartesi" -> sayiGun = 0
                            "Salı" -> sayiGun = 1
                            "Çarşamba" -> sayiGun = 2
                            "Perşembe" -> sayiGun = 3
                            "Cuma" -> sayiGun = 4
                            "Cumartesi" -> sayiGun = 5
                            "Pazar" -> sayiGun = 6

                        }
                        val yemekTemp = Yemek(binding.etGunSecimi.text.toString(),binding.kahvaltGiris.text.toString(),binding.oglegiris.text.toString(),binding.aksamgiris.text.toString())
                        when (binding.etYemekHaneSec.text.toString()) {
                            "Yemekhane 1" -> sayiYemekhane = 0
                            "Yemekhane 2" -> sayiYemekhane = 1
                            "Yemekhane 3" -> sayiYemekhane = 2
                        }
                        when (sayiYemekhane) {
                            0 -> {
                                val key = databaseYemek.child("Yemekhane 1").child("haftalık").child(sayiGun.toString()).push().key
                                if (key == null) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Giriş Yapılamadı",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                haftalikList.clear()
                                haftalikList = haftalik1!!.haftalık!!
                                val postValues = yemekTemp.toMap()

                                val childUpdates = hashMapOf<String, Any>(
                                    "/Yemekhane 1/haftalık/$sayiGun" to postValues,
                                )

                                databaseYemek.updateChildren(childUpdates)


                            }
                            1 -> {
                                val key = databaseYemek.child("Yemekhane 2").child("haftalık").child(sayiGun.toString()).push().key
                                if (key == null) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Giriş Yapılamadı",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                haftalikList.clear()
                                haftalikList = haftalik2!!.haftalık!!
                                val postValues = yemekTemp.toMap()

                                val childUpdates = hashMapOf<String, Any>(
                                    "/Yemekhane 2/haftalık/$sayiGun" to postValues,
                                )

                                databaseYemek.updateChildren(childUpdates)



                            }
                            2 -> {
                                val key = databaseYemek.child("Yemekhane 3").child("haftalık").child(sayiGun.toString()).push().key
                                if (key == null) {
                                    Toast.makeText(
                                        requireContext(),
                                        "Giriş Yapılamadı",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                                haftalikList.clear()
                                haftalikList = haftalik3!!.haftalık!!
                                val postValues = yemekTemp.toMap()

                                val childUpdates = hashMapOf<String, Any>(
                                    "/Yemekhane 3/haftalık/$sayiGun" to postValues,
                                )

                                databaseYemek.updateChildren(childUpdates)


                            }
                        }


                    }
                }

                override fun onCancelled(error: DatabaseError) {
                }

            })
        }

        return binding.root
    }


    private fun createYemekhanePopMenu(anchor: View) = PopupMenu(requireContext(), anchor).apply {
        menu.run {
            add("Yemekhane 1")
            add("Yemekhane 2")
            add("Yemekhane 3")
            setOnMenuItemClickListener {
                it.isChecked = true
                yemekhaneDuzenleViewModel.name.value = it.title.toString()
                true
            }
            setGroupCheckable(0, true, true)
        }
    }

    private fun createGunlerPopMenu(anchor: View) = PopupMenu(requireContext(), anchor).apply {
        menu.run {
            add("Pazartesi")
            add("Salı")
            add("Çarşamba")
            add("Perşembe")
            add("Cuma")
            add("Cumartesi")
            add("Pazar")
            setOnMenuItemClickListener {
                it.isChecked = true
                yemekhaneDuzenleViewModel.date.value = it.title.toString()
                true
            }
            setGroupCheckable(0, true, true)
        }
    }

}

