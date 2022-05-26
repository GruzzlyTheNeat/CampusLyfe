package com.example.campuslyfe.koin

import com.example.campuslyfe.fragment.admin.EtkinlikEkleViewModel
import com.example.campuslyfe.fragment.admin.EtkinlikSilViewModel
import com.example.campuslyfe.fragment.admin.ToplulukEkleViewModel
import com.example.campuslyfe.fragment.admin.YemekhaneDuzenleViewModel
import com.example.campuslyfe.fragment.club.ClubViewModel
import com.example.campuslyfe.fragment.etkinlikler.EtkinliklerViewModel
import com.example.campuslyfe.fragment.food.FoodViewModel
import com.example.campuslyfe.fragment.profil.ProfilViewModel
import com.example.campuslyfe.fragment.signIn.SignInSignUpViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModule {

    val viewModelsModule = module {
        viewModel { SignInSignUpViewModel() }
        viewModel { EtkinlikEkleViewModel() }
        viewModel { ToplulukEkleViewModel() }
        viewModel { YemekhaneDuzenleViewModel() }
        viewModel { FoodViewModel() }
        viewModel { EtkinliklerViewModel() }
        viewModel { ClubViewModel() }
        viewModel { ProfilViewModel() }
        viewModel { EtkinlikSilViewModel() }
    }
}