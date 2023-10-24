package com.geetgobindsingh.doglist

import androidx.lifecycle.viewModelScope
import com.geetgobindsingh.core_ui.arch.BaseViewModel
import com.geetgobindsingh.core_ui.arch.DispatcherProvider
import com.geetgobindsingh.core_ui.state.ProgressBarState
import com.geetgobindsingh.dog_domain.usecase.FetchDogsUseCase
import com.geetgobindsingh.doglist.model.toUIDogModel
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DogListViewModel constructor(
    private val fetchDogsUseCase: FetchDogsUseCase, dispatcherProvider: DispatcherProvider
) : BaseViewModel<DogListState, DogListEffects, DogListEvents>(dispatcherProvider, DogListState()) {

    companion object {
        private const val TAG = "DogListViewModel"
    }

    init {
        processEvent(DogListEvents.SubscribeDogs)
        processEvent(DogListEvents.GetDogs)
    }


    override fun reduce(currentState: DogListState, event: DogListEvents): DogListState =
        when (event) {
            is DogListEvents.SubscribeDogs -> {
                subscribeDogs()
                currentState
            }

            is DogListEvents.UpdateViewState -> {
                event.paginationState.error?.let {
                    sendEffect(DogListEffects.ShowSnackBar(it))
                }
                currentState.copy(
                    dogs = event.paginationState.items.map { it.toUIDogModel() },
                    progressBarState = if (currentState.dogs.isEmpty() && event.paginationState.isLoading)
                        ProgressBarState.Loading else ProgressBarState.Idle,
                    isLoading = currentState.dogs.isNotEmpty() && event.paginationState.isLoading,
                    endReached = event.paginationState.endReached,
                )
            }

            is DogListEvents.GetDogs -> {
                getDogs()
                currentState
            }

            else -> {
                currentState
            }
        }


    private fun subscribeDogs() {
        fetchDogsUseCase().onEach { paginationState ->
            processEvent(DogListEvents.UpdateViewState(paginationState))
        }.flowOn(dispatcherProvider.io).launchIn(viewModelScope)
    }

    private fun getDogs() {
        fetchDogsUseCase.loadMore("").flowOn(dispatcherProvider.io).launchIn(viewModelScope)
    }
}