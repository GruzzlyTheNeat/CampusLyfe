package com.example.campuslyfe.koin

import com.example.campuslyfe.fragment.admin.EtkinlikEkleViewModel
import com.example.campuslyfe.fragment.admin.ToplulukEkleViewModel
import com.example.campuslyfe.fragment.signIn.SignInSignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {

    val viewModelsModule = module {
        viewModel { SignInSignUpViewModel() }
        viewModel { EtkinlikEkleViewModel() }
        viewModel { ToplulukEkleViewModel() }
    }
}