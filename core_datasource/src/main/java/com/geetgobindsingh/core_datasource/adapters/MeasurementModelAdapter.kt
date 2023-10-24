package com.geetgobindsingh.core_datasource.adapters

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class MeasurementModelAdapter : ColumnAdapter<MeasurementModel, String> {
    override fun decode(databaseValue: String): MeasurementModel {
        return Json.decodeFromString<MeasurementModel>(databaseValue)
    }

    override fun encode(value: MeasurementModel): String {
        return Json.encodeToString(value)
    }
}