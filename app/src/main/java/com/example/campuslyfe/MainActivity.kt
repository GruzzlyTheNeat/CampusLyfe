package com.example.campuslyfe

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.campuslyfe.databinding.ActivityMainBinding
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController


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

