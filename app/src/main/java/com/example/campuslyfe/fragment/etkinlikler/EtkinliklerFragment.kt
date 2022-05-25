package com.example.campuslyfe.fragment.etkinlikler

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.campuslyfe.databinding.FragmentEtkinliklerBinding
import com.example.campuslyfe.model.Etkinlik
import org.koin.androidx.viewmodel.ext.android.viewModel

class EtkinliklerFragment : Fragment(), EtkinliklerAdapter.OnEtkinlikClickListener {

    private val etkinliklerViewModel by viewModel<EtkinliklerViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentEtkinliklerBinding.inflate(inflater, container, false).apply {

            lifecycleOwner = viewLifecycleOwner

            etkinliklerViewModel.etkinlikler.observe(viewLifecycleOwner) {
                rvEtkinlikler.adapter =
                    EtkinliklerAdapter(
                        it,
                        requireContext(),
                        this@EtkinliklerFragment
                    )
            }


        }.root
    }

    override fun onEtkinlikClick(etkinlik: Etkinlik?) {
        etkinlik?.let {
            findNavController().navigate(
                EtkinliklerFragmentDirections
                    .actionEtkinliklerFragmentToEtkinlikDetayFragment(it)
            )
        }
    }
}