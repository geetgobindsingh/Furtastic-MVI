package com.geetgobindsingh.dog_domain.usecase

import com.geetgobindsingh.core.util.Resource
import com.geetgobindsingh.dog_domain.model.Dog
import com.geetgobindsingh.dog_domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow

class FetchDogDetailsUseCase(private val dogRepository: DogRepository) {
    operator fun invoke(
        dogId: Int
    ): Flow<Resource<Dog>> {
        return dogRepository.fetchDog(dogId)
    }
}