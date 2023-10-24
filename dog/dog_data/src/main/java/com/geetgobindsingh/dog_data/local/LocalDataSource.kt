package com.geetgobindsingh.dog_data.local

import com.geetgobindsingh.coredatasource.DogEntity

interface LocalDataSource {
    suspend fun getDogs(query: String, page: Int, pageSize: Int): List<DogEntity>
    suspend fun insertDog(dog: DogEntity)
    suspend fun removeAllDogs()
    suspend fun insertDogs(map: List<DogEntity>)
    suspend fun getDog(dogId: Int): DogEntity?
}