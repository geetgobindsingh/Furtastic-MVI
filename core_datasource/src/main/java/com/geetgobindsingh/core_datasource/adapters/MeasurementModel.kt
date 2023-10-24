package com.geetgobindsingh.core_datasource.adapters

@kotlinx.serialization.Serializable
data class MeasurementModel(
    val imperial: String? = null,
    val metric: String? = null
)
