package com.example.campuslyfe.fragment.etkinlikler

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.databinding.FragmentEtkinlikDetayBinding


class EtkinlikDetayFragment : Fragment() {

    private val navArgs by navArgs<EtkinlikDetayFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEtkinlikDetayBinding.inflate(inflater, container, false).apply {
            etkinlik = navArgs.etkinlik
            lifecycleOwner = viewLifecycleOwner

            etkinlikHaritadaGoster.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?saddr=20.344,34.34&daddr=20.5666,45.345")
                    )
                )
            }

        }.root
    }


}