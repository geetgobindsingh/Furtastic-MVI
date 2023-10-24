package com.geetgobindsingh.core_ui.di

import com.geetgobindsingh.core_ui.arch.DispatcherProvider
import com.geetgobindsingh.core_ui.arch.PlatformDispatcherProvider
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val coreUIModule = module {
    includes(CoilModule)
    singleOf(::PlatformDispatcherProvider) { bind<DispatcherProvider>() }
}