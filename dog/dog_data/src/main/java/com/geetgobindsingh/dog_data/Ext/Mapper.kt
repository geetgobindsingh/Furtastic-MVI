package com.geetgobindsingh.dog_data.Ext

import com.geetgobindsingh.coredatasource.DogEntity
import com.geetgobindsingh.dog_domain.model.Dog
import com.geetgobindsingh.dog_domain.model.Image
import com.geetgobindsingh.dog_domain.model.Measurement

fun DogEntity.toDomain(): Dog {
    return Dog(
        id = id.toInt(),
        weight = if (weight != null) Measurement(
            weight?.imperial ?: "NA",
            weight?.metric ?: "NA"
        ) else Measurement("NA", "NA"),
        height = if (height != null) Measurement(
            height?.imperial ?: "NA",
            height?.metric ?: "NA"
        ) else Measurement("NA", "NA"),
        name = name,
        bredFor = bredFor,
        breedGroup = breedGroup,
        lifeSpan = lifeSpan,
        temperament = temperament,
        origin = origin,
        referenceImageId = referenceImageId ?: "NA",
        image = image?.let { Image(image!!.id!!, image!!.width!!, image!!.height!!, image!!.url!!) } ?: Image.getDummy()
    )
}