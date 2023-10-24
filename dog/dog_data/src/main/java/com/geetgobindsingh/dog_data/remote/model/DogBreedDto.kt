package com.geetgobindsingh.dog_data.remote.model

import com.geetgobindsingh.core_datasource.adapters.ImageModel
import com.geetgobindsingh.core_datasource.adapters.MeasurementModel
import com.geetgobindsingh.coredatasource.DogEntity
import com.geetgobindsingh.dog_domain.model.Dog
import com.geetgobindsingh.dog_domain.model.Image
import com.geetgobindsingh.dog_domain.model.Measurement
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@kotlinx.serialization.Serializable
data class DogBreedDto(
    @SerialName("weight") val weight: MeasurementDto? = null,
    @SerialName("height") val height: MeasurementDto? = null,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String? = null,
    @SerialName("bred_for") val bredFor: String? = null,
    @SerialName("breed_group") val breedGroup: String? = null,
    @SerialName("life_span") val lifeSpan: String? = null,
    @SerialName("temperament") val temperament: String? = null,
    @SerialName("origin") val origin: String? = null,
    @SerialName("reference_image_id") val referenceImageId: String? = null,
    @SerialName("image") val image: ImageDto? = null,
)

@kotlinx.serialization.Serializable
data class MeasurementDto(
    @SerialName("imperial") val imperial: String? = null,
    @SerialName("metric") val metric: String? = null
)

@Serializable
data class ImageDto(
    @SerialName("id") val id: String? = null,
    @SerialName("width") val width: Int? = null,
    @SerialName("height") val height: Int? = null,
    @SerialName("url") val url: String
)

fun DogBreedDto.toDomain(): Dog {
    return Dog(
        id = id,
        weight = if (weight != null) {
            Measurement(weight.imperial ?: "NA", weight.metric ?: "NA")
        } else Measurement("NA", "NA"),
        height = if (height != null) {
            Measurement(height.imperial ?: "NA", height.metric ?: "NA")
        } else Measurement("NA", "NA"),
        name = name ?: "NA",
        bredFor = bredFor ?: "NA",
        breedGroup = breedGroup ?: "NA",
        lifeSpan = lifeSpan ?: "NA",
        temperament = temperament ?: "NA",
        origin = origin ?: "NA",
        referenceImageId = referenceImageId ?: "NA",
        image = image?.let {
            Image(image.id!!, image.width!!, image.height!!, image.url) ?: Image.getDummy()
        }
    )
}

fun DogBreedDto.toEntity(): DogEntity {
    return DogEntity(
        id = id.toLong(),
        weight = weight?.let { MeasurementModel(weight.imperial, weight.metric) },
        height = height?.let { MeasurementModel(height.imperial, height.metric) },
        name = name ?: "NA",
        bredFor = bredFor ?: "NA",
        breedGroup = breedGroup ?: "NA",
        lifeSpan = lifeSpan ?: "NA",
        temperament = temperament ?: "NA",
        origin = origin ?: "NA",
        referenceImageId = referenceImageId ?: "NA",
        image = image?.let { ImageModel(image.id, image.width, image.height, image.url) }
    )
}