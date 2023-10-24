package com.geetgobindsingh.core_network.di

import com.geetgobindsingh.core_network.Api
import com.geetgobindsingh.core_network.KtorClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.rmi.Naming.bind

val networkModule = module {
    singleOf(::KtorClient){ bind<Api>() }
}