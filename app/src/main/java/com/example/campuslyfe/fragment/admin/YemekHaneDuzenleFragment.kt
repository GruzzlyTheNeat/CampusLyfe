package com.example.campuslyfe.fragment.admin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import com.example.campuslyfe.databinding.FragmentYemekhaneDuzenleBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class YemekHaneDuzenleFragment: Fragment() {
    private val yemekhaneDuzenleViewModel by viewModel<YemekhaneDuzenleViewModel>()
    private lateinit var binding: FragmentYemekhaneDuzenleBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentYemekhaneDuzenleBinding.inflate(inflater, container, false)
        binding.viewModel = yemekhaneDuzenleViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        val yemekhane = createYemekhanePopMenu(binding.etYemekHaneSec)
        val günler = createGünlerPopMenu(binding.etGunSecimi)
        binding.etYemekHaneSec.setOnClickListener{yemekhane.show()}
        binding.etGunSecimi.setOnClickListener{günler.show()}

        return binding.root
    }


    fun createYemekhanePopMenu(anchor: View) = PopupMenu(requireContext(), anchor).apply {
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
                yemekhaneDuzenleViewModel.date.value = it.title.toString()
                true
            }
            setGroupCheckable(0, true, true)
        }
    }
}

