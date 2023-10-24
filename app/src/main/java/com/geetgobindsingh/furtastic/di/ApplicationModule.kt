package com.geetgobindsingh.furtastic.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val applicationScopeModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            schema = get(),
            context = androidApplication(),
            name = "FurtasticDatabase"
        )
    }
}