package com.geetgobindsingh.core_datasource.adapters

@kotlinx.serialization.Serializable
data class ImageModel(
    val id: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val url: String? = null
)