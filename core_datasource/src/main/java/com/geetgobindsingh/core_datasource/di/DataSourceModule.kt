package com.geetgobindsingh.core_datasource.di

import app.cash.sqldelight.db.QueryResult
import app.cash.sqldelight.db.SqlSchema
import com.geetgobindsingh.core_datasource.FurtasticDatabase
import com.geetgobindsingh.core_datasource.adapters.ImageModelAdapter
import com.geetgobindsingh.core_datasource.adapters.MeasurementModelAdapter
import com.geetgobindsingh.coredatasource.DogEntity
import com.geetgobindsingh.coredatasource.DogEntityQueries
import org.koin.dsl.module

val dataSourceModule = module {
    single<SqlSchema<QueryResult.Value<Unit>>> { FurtasticDatabase.Schema }
    single<DogEntityQueries> {
        FurtasticDatabase(
            get(),
            DogEntityAdapter = DogEntity.Adapter(
                imageAdapter = ImageModelAdapter(),
                weightAdapter = MeasurementModelAdapter(),
                heightAdapter = MeasurementModelAdapter()
            )
        ).dogEntityQueries
    }

}