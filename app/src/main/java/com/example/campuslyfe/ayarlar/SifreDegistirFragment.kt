package com.example.campuslyfe.ayarlar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campuslyfe.activity.SignInActivity
import com.example.campuslyfe.databinding.FragmentSifreDegistirBinding
import com.example.campuslyfe.utils.StateResource
import com.example.campuslyfe.utils.showToast
import org.koin.androidx.viewmodel.ext.android.viewModel

class SifreDegistirFragment : Fragment() {

    private val sifreDegistirViewModel by viewModel<SifreDegistirViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSifreDegistirBinding.inflate(inflater, container, false)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sifreDegistirViewModel
            btKayderYeniPassword.setOnClickListener {
                if (binding.etSifreDegistirCurrentPassword.text.isNotEmpty() && binding.etSifreDegistirNewPassword.text.isNotEmpty()) {
                    sifreDegistirViewModel.updatePassword()
                } else
                    showToast("Lütfen bütün alanları doldurunuz")
            }
        }

        sifreDegistirViewModel.passwordUpdateState.observe(viewLifecycleOwner) {
            when (it) {
                is StateResource.Loading -> Unit
                is StateResource.Success -> {
                    showToast("Şifre Başarıyla Değiştirildi")
                    startActivity(
                        Intent(requireContext(), SignInActivity::class.java)
                    )
                    activity?.finish()
                }
                is StateResource.Error -> {
                    showToast("Lütfen bütün alanları doldurunuz")
                    it.e?.printStackTrace()
                }
            }
        }
        return binding.root

    }
}