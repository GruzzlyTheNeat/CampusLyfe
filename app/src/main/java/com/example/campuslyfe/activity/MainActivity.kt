package com.example.campuslyfe.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.campuslyfe.databinding.ActivityMainBinding
import androidx.navigation.ui.setupWithNavController
import com.example.campuslyfe.R


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    val navController: NavController by lazy { findNavController(R.id.fragmentContainerView2) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.bottomNavigation.setupWithNavController(navController)
        binding.bottomNavigation.setOnItemReselectedListener {
            when(it.itemId){
                R.id.mainFragment ->{
                    navController.navigate(R.id.mainFragment)
                }
                R.id.profilFragment -> {
                    navController.navigate(R.id.profilFragment)
                }
                R.id.ayarlarFragment ->{
                    navController.navigate(R.id.ayarlarFragment)
                }
            }
        }





    }

}

