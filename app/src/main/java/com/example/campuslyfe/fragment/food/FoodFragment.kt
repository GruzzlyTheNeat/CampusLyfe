package com.example.campuslyfe.fragment.food

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.example.campuslyfe.databinding.FragmentFoodBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class FoodFragment : Fragment() {

    private val foodViewModel by viewModel<FoodViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentFoodBinding.inflate(inflater, container, false).apply {
            viewModel = foodViewModel
            lifecycleOwner = viewLifecycleOwner
            val yemekhane = createYemekhanePopMenu(dropdownYemekhaneSeciniz)
            val gunler = createGunlerPopMenu(dropdownGunSeciniz)
            dropdownYemekhaneSeciniz.setOnClickListener { yemekhane.show() }
            dropdownGunSeciniz.setOnClickListener { gunler.show() }
        }.root
    }

    private fun createYemekhanePopMenu(anchor: View) = PopupMenu(requireContext(), anchor).apply {
        menu.run {
            add("Yemekhane 1")
            add("Yemekhane 2")
            add("Yemekhane 3")
            setOnMenuItemClickListener {
                it.isChecked = true
                foodViewModel.selectedYemekhane.postValue(it.title.toString())
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
                foodViewModel.selectedGun.postValue(it.title.toString())
                true
            }
            setGroupCheckable(0, true, true)
        }
    }

}



