package com.geetgobindsingh.dog_data.local

import com.geetgobindsingh.coredatasource.DogEntity
import com.geetgobindsingh.coredatasource.DogEntityQueries


class LocalDataSourceImpl(private val dogEntityQueries: DogEntityQueries) : LocalDataSource {
    override suspend fun getDogs(query: String, page: Int, pageSize: Int): List<DogEntity> {
        return dogEntityQueries.fetchAllDogs(pageSize.toLong(), page.toLong() * pageSize.toLong())
            .executeAsList()
    }

    override suspend fun insertDog(dog: DogEntity) {
        dogEntityQueries.insertOrReplaceDog(
            id = dog.id,
            name = dog.name,
            weight = dog.weight,
            height = dog.height,
            bredFor = dog.bredFor,
            breedGroup = dog.breedGroup,
            lifeSpan = dog.lifeSpan,
            temperament = dog.temperament,
            origin = dog.origin,
            referenceImageId = dog.referenceImageId,
            image = dog.image
        )
    }

    override suspend fun removeAllDogs() {
        dogEntityQueries.removeAllDogs()
    }

    override suspend fun insertDogs(map: List<DogEntity>) {
        dogEntityQueries.transaction {
            map.forEach {
                dogEntityQueries.insertOrReplaceDog(
                    id = it.id,
                    name = it.name,
                    weight = it.weight,
                    height = it.height,
                    bredFor = it.bredFor,
                    breedGroup = it.breedGroup,
                    lifeSpan = it.lifeSpan,
                    temperament = it.temperament,
                    origin = it.origin,
                    referenceImageId = it.referenceImageId,
                    image = it.image
                )
            }
        }
    }

    override suspend fun getDog(dogId: Int): DogEntity? {
        return dogEntityQueries.getDog(dogId.toLong()).executeAsOneOrNull()
    }
}