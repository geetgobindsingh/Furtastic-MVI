package com.geetgobindsingh.dog_domain.usecase

import com.geetgobindsingh.dog_domain.model.Dog
import com.geetgobindsingh.dog_domain.pagination.DefaultPaginator
import com.geetgobindsingh.dog_domain.repository.DogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update

class FetchDogsUseCase(private val dogRepository: DogRepository) {

    data class PaginationState(
        var isLoading: Boolean = false,
        val items: List<Dog> = emptyList(),
        val error: String? = null,
        val endReached: Boolean = false,
        val pageIndex: Int = 0,
        val pageSize: Int = 20
    )

    var state = MutableStateFlow(PaginationState())

    private val paginator = DefaultPaginator(
        initialKey = state.value.pageIndex,
        onLoadUpdated = { value ->
            state.update { it.copy(isLoading = value) }
        },
        onRequest = { query, nextPage ->
            dogRepository.fetchDogs(query, nextPage, state.value.pageSize)
        },
        getNextKey = {
            state.value.pageIndex + 1
        },
        onError = { error ->
            state.update { it.copy(error = error?.localizedMessage) }
            state.update { it.copy(error = null) } // reset to ping error only once on UI
        },
        onSuccess = { items, newKey ->
            state.update {
                it.copy(
                    items = it.items + items,
                    pageIndex = newKey,
                    endReached = items.isEmpty()
                )
            }
        },
        stopRequesting = {
            return@DefaultPaginator state.value.endReached
        }
    )


    operator fun invoke(
    ): MutableStateFlow<PaginationState> {
        return state
    }

    public fun loadMore(query: String) = flow<Nothing> {
        paginator.loadNextItems(query)
    }
}