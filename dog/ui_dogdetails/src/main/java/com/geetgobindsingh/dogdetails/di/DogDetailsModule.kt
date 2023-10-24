package com.geetgobindsingh.dogdetails.di

import com.geetgobindsingh.core_ui.di.coreUIModule
import com.geetgobindsingh.dog_data.di.dogDataModule
import com.geetgobindsingh.dog_domain.di.dogDomainModule
import com.geetgobindsingh.dogdetails.DogDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val dogDetailsModule = module {
    includes(listOf(dogDataModule, dogDomainModule, coreUIModule))
    viewModelOf(::DogDetailsViewModel)
}
