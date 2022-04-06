package com.example.campuslyfe.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.campuslyfe.R
import com.example.campuslyfe.databinding.ActivityMainBinding
import com.example.campuslyfe.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}