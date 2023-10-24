package com.geetgobindsingh.dog_domain.repository

import com.geetgobindsingh.core.util.Resource
import com.geetgobindsingh.dog_domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogRepository {
    suspend fun fetchDogs(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<Dog>>

    fun fetchDog(dogId: Int): Flow<Resource<Dog>>
}