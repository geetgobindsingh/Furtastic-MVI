package com.geetgobindsingh.dog_data.remote

import com.geetgobindsingh.dog_data.remote.model.DogBreedDto

interface RemoteDataSource {
    suspend fun getDogBreeds(query: String, page: Int, pageSize: Int): List<DogBreedDto>
    suspend fun getDog(dogId: Int): DogBreedDto
}