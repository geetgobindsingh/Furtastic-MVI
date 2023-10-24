package com.geetgobindsingh.dog_data.di

import com.geetgobindsingh.core_datasource.di.dataSourceModule
import com.geetgobindsingh.core_network.di.networkModule
import com.geetgobindsingh.dog_data.local.LocalDataSource
import com.geetgobindsingh.dog_data.local.LocalDataSourceImpl
import com.geetgobindsingh.dog_data.remote.RemoteDataSource
import com.geetgobindsingh.dog_data.remote.RemoteDataSourceImpl
import com.geetgobindsingh.dog_data.repository.DogRepositoryImpl
import com.geetgobindsingh.dog_domain.repository.DogRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dogDataModule = module {
    includes(listOf(dataSourceModule, networkModule))
    singleOf(::LocalDataSourceImpl) { bind<LocalDataSource>() }
    singleOf(::RemoteDataSourceImpl) { bind<RemoteDataSource>() }
    singleOf(::DogRepositoryImpl) { bind<DogRepository>() }
}