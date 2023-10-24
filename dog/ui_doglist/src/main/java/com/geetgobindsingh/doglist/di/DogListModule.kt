package com.geetgobindsingh.doglist.di

import com.geetgobindsingh.core_ui.di.coreUIModule
import com.geetgobindsingh.dog_data.di.dogDataModule
import com.geetgobindsingh.dog_domain.di.dogDomainModule
import com.geetgobindsingh.doglist.DogListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val dogListModule = module {
    includes(listOf(dogDataModule, dogDomainModule, coreUIModule))
    viewModelOf(::DogListViewModel)
}
