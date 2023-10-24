package com.geetgobindsingh.dog_data.remote

import com.geetgobindsingh.core_network.Api
import com.geetgobindsingh.core_network.EndPoints
import com.geetgobindsingh.dog_data.remote.model.DogBreedDto
import io.ktor.client.request.get
import io.ktor.client.request.url

class RemoteDataSourceImpl(private val api: Api) :
    RemoteDataSource {
    override suspend fun getDogBreeds(query: String, page: Int, pageSize: Int): List<DogBreedDto> =
        api.getClient().get<List<DogBreedDto>> {
            url(EndPoints.BREEDS + "?limit=" + pageSize + "&page=" + page)
        }

    override suspend fun getDog(dogId: Int): DogBreedDto {
        return api.getClient().get<DogBreedDto> {
            url(EndPoints.BREEDS + "/" + dogId)
        }
    }

}