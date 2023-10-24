package com.geetgobindsingh.dog_data.repository

import com.geetgobindsingh.core.util.Resource
import com.geetgobindsingh.coredatasource.DogEntity
import com.geetgobindsingh.dog_data.Ext.toDomain
import com.geetgobindsingh.dog_data.local.LocalDataSource
import com.geetgobindsingh.dog_data.remote.RemoteDataSource
import com.geetgobindsingh.dog_data.remote.model.DogBreedDto
import com.geetgobindsingh.dog_data.remote.model.toEntity
import com.geetgobindsingh.dog_domain.model.Dog
import com.geetgobindsingh.dog_domain.repository.DogRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException

class DogRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : DogRepository {

    override suspend fun fetchDogs(query: String, page: Int, pageSize: Int): Result<List<Dog>> {
        return try {
            var localResult = localDataSource.getDogs(query, page, pageSize)
            if (!localResult.isEmpty()) {
                return Result.success(localResult.map { it.toDomain() })
            } else {
                val serverResult: List<DogBreedDto> =
                    remoteDataSource.getDogBreeds(query, page, pageSize)
                localDataSource.insertDogs(serverResult.map { it.toEntity() })
                localResult = localDataSource.getDogs(query, page, pageSize)
                return Result.success(localResult.map { it.toDomain() })
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override fun fetchDog(dogId: Int): Flow<Resource<Dog>> {
        return flow {
            emit(Resource.Loading(true))
            val dogEntity: DogEntity? = localDataSource.getDog(dogId)
            if (dogEntity == null) {
                val dogBreedDto: DogBreedDto? = try {
                    remoteDataSource.getDog(dogId)
                } catch (e: IOException) {
                    e.printStackTrace()
                    emit(Resource.Error("Couldn't load data, no internet connection!"))
                    emit(Resource.Loading(false))
                    null
                } catch (e: Exception) {
                    e.printStackTrace()
                    emit(Resource.Error("Dog Not Found!"))
                    emit(Resource.Loading(false))
                    null
                }
                dogBreedDto?.let {
                    localDataSource.insertDog(it.toEntity())
                    emit(Resource.Success(data = localDataSource.getDog(dogId)!!.toDomain()))
                    emit(Resource.Loading(false))
                }
            } else {
                emit(Resource.Success(data = dogEntity.toDomain()))
                emit(Resource.Loading(false))
            }
        }
    }
}