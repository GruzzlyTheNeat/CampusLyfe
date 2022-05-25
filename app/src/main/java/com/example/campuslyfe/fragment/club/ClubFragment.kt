package com.example.campuslyfe.fragment.club

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campuslyfe.databinding.FragmentClubBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClubFragment : Fragment() {

    private val clubViewModel by viewModel<ClubViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return FragmentClubBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            clubViewModel.topluluklar.observe(viewLifecycleOwner) {
                recyclerViewClub.adapter = ClubRwAdapter(it)
            }
        }.root
    }


}
