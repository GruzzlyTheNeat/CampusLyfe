package com.example.campuslyfe.fragment.food

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.example.campuslyfe.data.grabFoodFromDB
import com.example.campuslyfe.model.Yemek
import com.example.campuslyfe.databinding.FragmentFoodBinding

class FoodFragment : Fragment() {
    private lateinit var binding: FragmentFoodBinding
    lateinit var Gunluk: Yemek
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFoodBinding.inflate(inflater,container,false)
        val yemekhane = createYemekhanePopMenu(binding.textView2)
        val günler = createGünlerPopMenu(binding.textView3)
        var sayı = 7
        when(binding.textView3.text){
            "Pazartesi" -> sayı = 0
            "Salı" -> sayı = 1
            "Çarşamba" -> sayı = 2
            "Perşembe" -> sayı = 3
            "Cuma" -> sayı = 4
            "Cumartesi" -> sayı = 5
            "Pazar" -> sayı = 6

        }
        binding.textView2.setOnClickListener{yemekhane.show()}
        binding.textView3.setOnClickListener{günler.show()}
        binding.textView4.setOnClickListener{
            val tempFood = grabFoodFromDB().asOne(binding.textView2.text.toString())
            tempFood.haftalık.forEach{
                if(it.gunname == binding.textView3.text){
                    Gunluk = it
                }
            }
            binding.textView5.text = Gunluk.toStringSabah()
            binding.textView6.text = Gunluk.toStringOgle()
            binding.textView7.text = Gunluk.toStringAksam()


        }
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

}



