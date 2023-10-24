package com.geetgobindsingh.core_datasource.adapters

import app.cash.sqldelight.ColumnAdapter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ImageModelAdapter : ColumnAdapter<ImageModel, String> {
    override fun decode(databaseValue: String): ImageModel {
        return Json.decodeFromString<ImageModel>(databaseValue)
    }

    override fun encode(value: ImageModel): String {
        return Json.encodeToString(value)
    }
}