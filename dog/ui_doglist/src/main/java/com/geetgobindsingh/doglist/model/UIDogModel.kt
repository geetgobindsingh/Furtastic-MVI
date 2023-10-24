package com.geetgobindsingh.doglist.model

import com.geetgobindsingh.dog_domain.model.Dog
import com.geetgobindsingh.dog_domain.model.Image


data class UIDogModel(val id: Int, val name: String, val image: Image)

fun Dog.toUIDogModel(): UIDogModel {
    return UIDogModel(
        id, name, image ?: Image.getDummy()
    )
}