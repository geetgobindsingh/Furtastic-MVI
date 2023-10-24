package com.geetgobindsingh.dog_domain.model

import kotlin.random.Random

data class Dog(
    val weight: Measurement,
    val height: Measurement,
    val id: Int,
    val name: String,
    val bredFor: String,
    val breedGroup: String,
    val lifeSpan: String,
    val temperament: String,
    val origin: String,
    val referenceImageId: String,
    val image: Image? = null,
)

data class Measurement(
    val imperial: String,
    val metric: String
)

data class Image(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String
) {
    companion object {
        fun getDummy(): Image {
            return Image(
                Random.nextInt(100).toString(),
                100,
                100,
                "https://demofree.sirv.com/nope-not-here.jpg"
            )
        }
    }
}