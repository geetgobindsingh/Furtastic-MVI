package com.geetgobindsingh.furtastic.di

import android.app.Application
import com.geetgobindsingh.dogdetails.di.dogDetailsModule
import com.geetgobindsingh.doglist.di.dogListModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

fun Application.initKoin() {
    startKoin {
        androidContext(this@initKoin)

        modules(koinModules)
    }
}

val koinModules = listOf(
    applicationScopeModule,
    dogListModule,
    dogDetailsModule
)