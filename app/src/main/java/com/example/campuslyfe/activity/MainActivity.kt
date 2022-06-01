package com.example.campuslyfe.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.campuslyfe.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController
import com.example.campuslyfe.R
import com.example.campuslyfe.data.sendToDB
import com.example.campuslyfe.model.*


class MainActivity : AppCompatActivity() {
    private val navController: NavController by lazy { findNavController(R.id.fragmentContainerView2) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.bottomNavigation.apply {
            setupWithNavController(navController)
            setOnItemReselectedListener { menuItem ->
                navController.navigate(
                    when (menuItem.itemId) {
                        R.id.mainFragment -> R.id.mainFragment
                        R.id.profilFragment -> R.id.profilFragment
                        else -> R.id.ayarlarFragment
                    }
                )
            }
        }

    }
}

