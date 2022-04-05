package com.example.campuslyfe

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import com.example.campuslyfe.databinding.FragmentMainBinding


class MainFragment : Fragment() {
    private lateinit var binding : FragmentMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater,container,false)
        val buttonEtkinlik : CardView = binding.cardView1

        buttonEtkinlik.setOnClickListener {
            println("basıldı")
        }
        return binding.root
    }


}