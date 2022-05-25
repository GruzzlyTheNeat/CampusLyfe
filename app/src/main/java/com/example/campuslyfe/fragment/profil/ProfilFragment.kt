package com.example.campuslyfe.fragment.profil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentProfilBinding
import com.example.campuslyfe.utils.downloadFromURL
import com.example.campuslyfe.utils.placeHolderProgressBar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfilFragment : Fragment() {

    private val profilViewModel by viewModel<ProfilViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentProfilBinding.inflate(inflater, container, false).apply {

            lifecycleOwner = viewLifecycleOwner
            viewModel = profilViewModel
            profilViewModel.imageUrl.observe(viewLifecycleOwner) {
                if (it != null)
                    imgProfil.downloadFromURL(
                        it.toString(),
                        placeHolderProgressBar(requireContext())
                    )
                else
                    imgProfil.setImageResource(R.drawable.ic_baseline_person_24)
            }
        }.root
    }


}