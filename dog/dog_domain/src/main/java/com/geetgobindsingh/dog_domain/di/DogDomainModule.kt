package com.geetgobindsingh.dog_domain.di

import com.geetgobindsingh.dog_domain.usecase.FetchDogDetailsUseCase
import com.geetgobindsingh.dog_domain.usecase.FetchDogsUseCase
import org.koin.dsl.module

val dogDomainModule = module {
    single<FetchDogsUseCase> { FetchDogsUseCase(get()) }
    single<FetchDogDetailsUseCase> { FetchDogDetailsUseCase(get()) }
}