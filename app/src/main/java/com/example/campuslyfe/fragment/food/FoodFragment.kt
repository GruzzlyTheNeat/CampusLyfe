package com.example.campuslyfe.fragment.food

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.example.campuslyfe.model.Yemek
import com.example.campuslyfe.databinding.FragmentFoodBinding
import com.example.campuslyfe.model.Yemekhane
import com.example.campuslyfe.utils.getDatabaseInstance
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class FoodFragment : Fragment() {
    private lateinit var binding: FragmentFoodBinding

    private lateinit var yemekhaneList: ArrayList<Yemekhane>
    private var haftalik1: Yemekhane? = null
    private var haftalik2: Yemekhane? = null
    private var haftalik3: Yemekhane? = null
    private lateinit var haftalikList: ArrayList<Yemek>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFoodBinding.inflate(inflater, container, false)
        val yemekhane = createYemekhanePopMenu(binding.textView2)
        val gunler = createGunlerPopMenu(binding.textView3)
        var sayiGun = 7
        var sayiYemekhane = 3
        binding.textView2.setOnClickListener { yemekhane.show() }
        binding.textView3.setOnClickListener { gunler.show() }
        binding.textView4.setOnClickListener {
            val databaseYemek =
                getDatabaseInstance()
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
                        haftalik1 = yemekhaneList[0]
                        haftalik2 = yemekhaneList[1]
                        haftalik3 = yemekhaneList[2]
                        when (binding.textView3.text) {
                            "Pazartesi" -> sayiGun = 0
                            "Salı" -> sayiGun = 1
                            "Çarşamba" -> sayiGun = 2
                            "Perşembe" -> sayiGun = 3
                            "Cuma" -> sayiGun = 4
                            "Cumartesi" -> sayiGun = 5
                            "Pazar" -> sayiGun = 6

                        }
                        when (binding.textView2.text) {
                            "Yemekhane 1" -> sayiYemekhane = 0
                            "Yemekhane 2" -> sayiYemekhane = 1
                            "Yemekhane 3" -> sayiYemekhane = 2
                        }
                        when (sayiYemekhane) {
                            0 -> {
                                haftalikList.clear()
                                haftalikList = haftalik1!!.haftalık!!
                                gunYerlestir(sayiGun)
                            }
                            1 -> {
                                haftalikList.clear()
                                haftalikList = haftalik2!!.haftalık!!
                                gunYerlestir(sayiGun)
                            }
                            2 -> {
                                haftalikList.clear()
                                haftalikList = haftalik3!!.haftalık!!
                                gunYerlestir(sayiGun)
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

    private fun gunYerlestir(sayiGun: Int) {
        when (sayiGun) {
            0 -> {
                binding.textView5.text = haftalikList[0].toStringSabah()
                binding.textView6.text = haftalikList[0].toStringOgle()
                binding.textView7.text = haftalikList[0].toStringAksam()
            }
            1 -> {
                binding.textView5.text = haftalikList[1].toStringSabah()
                binding.textView6.text = haftalikList[1].toStringOgle()
                binding.textView7.text = haftalikList[1].toStringAksam()
            }
            2 -> {
                binding.textView5.text = haftalikList[2].toStringSabah()
                binding.textView6.text = haftalikList[2].toStringOgle()
                binding.textView7.text = haftalikList[2].toStringAksam()
            }
            3 -> {
                binding.textView5.text = haftalikList[3].toStringSabah()
                binding.textView6.text = haftalikList[3].toStringOgle()
                binding.textView7.text = haftalikList[3].toStringAksam()
            }
            4 -> {
                binding.textView5.text = haftalikList[4].toStringSabah()
                binding.textView6.text = haftalikList[4].toStringOgle()
                binding.textView7.text = haftalikList[4].toStringAksam()
            }
            5 -> {
                binding.textView5.text = haftalikList[5].toStringSabah()
                binding.textView6.text = haftalikList[5].toStringOgle()
                binding.textView7.text = haftalikList[5].toStringAksam()
            }
            6 -> {
                binding.textView5.text = haftalikList[6].toStringSabah()
                binding.textView6.text = haftalikList[6].toStringOgle()
                binding.textView7.text = haftalikList[6].toStringAksam()
            }
        }
    }

    private fun createYemekhanePopMenu(anchor: View) = PopupMenu(requireContext(), anchor).apply {
        menu.run {
            add("Yemekhane 1")
            add("Yemekhane 2")
            add("Yemekhane 3")
            setOnMenuItemClickListener {
                it.isChecked = true
                binding.textView2.text = it.title
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
                binding.textView3.text = it.title
                true
            }
            setGroupCheckable(0, true, true)
        }
    }

}



