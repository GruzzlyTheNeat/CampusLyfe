package com.example.campuslyfe.ayarlar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.campuslyfe.activity.SignInActivity
import com.example.campuslyfe.databinding.FragmentSifreDegistirBinding
import com.example.campuslyfe.utils.showToast
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class SifreDegistirFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = FragmentSifreDegistirBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()

        binding.btKayderYeniPassword.setOnClickListener {
            if (binding.etSifreDegistirCurrentPassword.text.isNotEmpty() && binding.etSifreDegistirNewPassword.text.isNotEmpty()) {
                val user = auth.currentUser ?: return@setOnClickListener

                val credential = EmailAuthProvider.getCredential(
                    user.email.toString(),
                    binding.etSifreDegistirCurrentPassword.text.toString()
                )
                user.reauthenticate(credential).addOnCompleteListener {
                    if (it.isSuccessful) {
                        user.updatePassword(binding.etSifreDegistirNewPassword.text.toString())
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    showToast("Şifre Başarıyla Değiştirildi")
                                    auth.signOut()
                                    startActivity(
                                        Intent(requireContext(), SignInActivity::class.java)
                                    )
                                    activity?.finish()
                                }
                            }

                    } else
                        showToast("Lütfen bütün alanları doldurunuz")
                }

            } else
                showToast("Lütfen bütün alanları doldurunuz")

        }
        return binding.root

    }

    private fun changePassword() {

    }


}