package com.example.campuslyfe.fragment.etkinlikler

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.FragmentEtkinlikKatilimciListBinding
import com.example.campuslyfe.databinding.FragmentKatilimciProfilBinding
import com.example.campuslyfe.utils.downloadFromURL
import com.example.campuslyfe.utils.getDatabaseInstance
import com.example.campuslyfe.utils.placeHolderProgressBar
import com.google.firebase.storage.FirebaseStorage


class KatilimciProfilFragment : Fragment() {

    private lateinit var binding: FragmentKatilimciProfilBinding
    private val argsUser by navArgs<KatilimciProfilFragmentArgs>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentKatilimciProfilBinding.inflate(inflater, container, false)
        binding.tvAdKatilimci.text = argsUser.user.adSoyad
        binding.tvBilgiKatilimci.text = argsUser.user.bilgi
        binding.tvBolumKatilimci.text = argsUser.user.bolum
        val uid = argsUser.user.userId.toString()
        val imagePath = "users/$uid"
        val storageRef = FirebaseStorage.getInstance().reference
        val imageRef = storageRef.child(imagePath)
        imageRef.downloadUrl.addOnSuccessListener {
            binding.imgKatilimci.downloadFromURL(it.toString(), placeHolderProgressBar(requireContext()))
        }

        binding.buttonEtkinlikleriGoster.setOnClickListener {
            val action = KatilimciProfilFragmentDirections.actionKatilimciProfilFragmentToKatilinilanEtkinliklerFragment(argsUser.user)
            findNavController().navigate(action)
        }




        return binding.root
    }


}