package com.example.campuslyfe.ayarlar

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.campuslyfe.R
import com.example.campuslyfe.activity.MainActivity
import com.example.campuslyfe.activity.SignInActivity
import com.example.campuslyfe.databinding.FragmentClubBinding
import com.example.campuslyfe.databinding.FragmentSifreDegistirBinding
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class SifreDegistirFragment : Fragment() {

    private lateinit var binding : FragmentSifreDegistirBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentSifreDegistirBinding.inflate(inflater,container,false)
        auth = FirebaseAuth.getInstance()

        binding.btKayderYeniPassword.setOnClickListener {
            if(binding.etSifreDegistirCurrentPassword.text.isNotEmpty() && binding.etSifreDegistirNewPassword.text.isNotEmpty()){
                val user = auth.currentUser
                if(user != null){
                    val credential = EmailAuthProvider.getCredential(user.email.toString(),binding.etSifreDegistirCurrentPassword.text.toString())
                    user?.reauthenticate(credential)?.addOnCompleteListener {
                        if(it.isSuccessful){
                            user?.updatePassword(binding.etSifreDegistirNewPassword.text.toString())?.addOnCompleteListener { task ->
                                if(task.isSuccessful){
                                    Toast.makeText(requireContext(),"Şifre Başarıyla Değiştirildi",Toast.LENGTH_LONG).show()
                                    auth.signOut()
                                    startActivity(
                                        Intent(requireContext(), SignInActivity::class.java)
                                    )
                                    activity?.finish()
                                }
                            }

                        }
                        else{
                            Toast.makeText(requireContext(),"Lütfen bütün alanları doldurunuz",Toast.LENGTH_LONG).show()

                        }
                    }
                }



            }
            else{
                Toast.makeText(requireContext(),"Lütfen bütün alanları doldurunuz",Toast.LENGTH_LONG).show()
            }
        }
        return binding.root

    }

    private fun changePassword(){

    }


}