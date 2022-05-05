package com.example.campuslyfe.fragment.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentFoodBinding


class FoodFragment : Fragment() {
    private lateinit var binding: FragmentFoodBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater,container,false)
        val yemekhane = createYemekhanePopMenu(binding.textView2)
        val günler = createGünlerPopMenu(binding.textView3)
        binding.textView2.setOnClickListener{yemekhane.show()}
        binding.textView3.setOnClickListener{günler.show()}
        binding.textView4.setOnClickListener{yemekgelsin(binding.textView2.text as String, binding.textView3.text as String)}
        // Inflate the layout for this fragment
        return binding.root
    }
    fun createYemekhanePopMenu(anchor: View) = PopupMenu(requireContext(), anchor).apply {
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

    fun createGünlerPopMenu(anchor: View) = PopupMenu(requireContext(), anchor).apply {
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

    fun yemekgelsin(a: String, b: String){
        if(a == "Yemekhane 1"){
            when(b){
                "Pazartesi" -> {binding.textView5.text = yemekhane1[0][0]
                    binding.textView6.text = yemekhane1[0][1]}
                "Salı" -> binding.textView5.text = yemekhane1[1][0]
                "Çarşamba" -> binding.textView5.text = yemekhane1[2][0]
                "Perşembe" -> binding.textView5.text = yemekhane1[3][0]
                "Cuma" -> binding.textView5.text = yemekhane1[4][0]
                "Cumartesi" -> binding.textView5.text = yemekhane1[5][0]
                "Pazar" -> binding.textView5.text = yemekhane1[6][0]
            }
        }
        else if(a == "Yemekhane 2"){
            when(b){
                "Pazartesi" -> binding.textView5.text = yemekhane2[0][0]
                "Salı" -> binding.textView5.text = yemekhane2[1][0]
                "Çarşamba" -> binding.textView5.text = yemekhane2[2][0]
                "Perşembe" -> binding.textView5.text = yemekhane2[3][0]
                "Cuma" -> binding.textView5.text = yemekhane2[4][0]
                "Cumartesi" -> binding.textView5.text = yemekhane2[5][0]
                "Pazar" -> binding.textView5.text = yemekhane2[6][0]
            }
        }
        else{
            when(b){
                "Pazartesi" -> binding.textView5.text = yemekhane3[0][0]
                "Salı" -> binding.textView5.text = yemekhane3[1][0]
                "Çarşamba" -> binding.textView5.text = yemekhane3[2][0]
                "Perşembe" -> binding.textView5.text = yemekhane3[3][0]
                "Cuma" -> binding.textView5.text = yemekhane3[4][0]
                "Cumartesi" -> binding.textView5.text = yemekhane3[5][0]
                "Pazar" -> binding.textView5.text = yemekhane3[6][0]
            }
        }
        binding.textView5.visibility= android.view.View.VISIBLE
        binding.textView6.visibility= android.view.View.VISIBLE
        binding.textView7.visibility= android.view.View.VISIBLE

    }

    val yemekhane1 = arrayOf(arrayOf("yh1kahvalti1","yh1öğlen1", "yh1akşam1"),
                             arrayOf("yh1kahvalti2","yh1öğlen2", "yh1akşam2"),
                             arrayOf("yh1kahvalti3","yh1öğlen3", "yh1akşam3"),
                             arrayOf("yh1kahvalti4","yh1öğlen4", "yh1akşam4"),
                             arrayOf("yh1kahvalti5","yh1öğlen5", "yh1akşam5"),
                             arrayOf("yh1kahvalti6","yh1öğlen6", "yh1akşam6"),
                             arrayOf("yh1kahvalti7","yh1öğlen7", "yh1akşam7")
                                )
    val yemekhane2 = arrayOf(arrayOf("yh2kahvalti1","yh2öğlen1", "yh2akşam1"),
        arrayOf("yh2kahvalti2","yh2öğlen2", "yh2akşam2"),
        arrayOf("yh2kahvalti3","yh2öğlen3", "yh2akşam3"),
        arrayOf("yh2kahvalti4","yh2öğlen4", "yh2akşam4"),
        arrayOf("yh2kahvalti5","yh2öğlen5", "yh2akşam5"),
        arrayOf("yh2kahvalti6","yh2öğlen6", "yh2akşam6"),
        arrayOf("yh2kahvalti7","yh2öğlen7", "yh2akşam7")
    )
    val yemekhane3 = arrayOf(arrayOf("yh3kahvalti1","yh3öğlen1", "yh3akşam1"),
        arrayOf("yh3kahvalti2","yh3öğlen2", "yh3akşam2"),
        arrayOf("yh3kahvalti3","yh3öğlen3", "yh3akşam3"),
        arrayOf("yh3kahvalti4","yh3öğlen4", "yh3akşam4"),
        arrayOf("yh3kahvalti5","yh3öğlen5", "yh3akşam5"),
        arrayOf("yh3kahvalti6","yh3öğlen6", "yh3akşam6"),
        arrayOf("yh3kahvalti7","yh3öğlen7", "yh3akşam7")
    )

}



