package com.geetgobindsingh.dogdetails

import androidx.lifecycle.viewModelScope
import com.geetgobindsingh.core.util.Resource
import com.geetgobindsingh.core_ui.arch.BaseViewModel
import com.geetgobindsingh.core_ui.arch.DispatcherProvider
import com.geetgobindsingh.core_ui.state.ProgressBarState
import com.geetgobindsingh.core_ui.state.UIComponent
import com.geetgobindsingh.dog_domain.usecase.FetchDogDetailsUseCase
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DogDetailsViewModel constructor(
    private val fetchDogDetailsUseCase: FetchDogDetailsUseCase,
    dispatcherProvider: DispatcherProvider
) : BaseViewModel<DogDetailsState, DogDetailsEffects, DogDetailsEvents>(
    dispatcherProvider,
    DogDetailsState()
) {

    companion object {
        private const val TAG = "DogDetailsViewModel"
    }


    override fun reduce(currentState: DogDetailsState, event: DogDetailsEvents): DogDetailsState =
        when (event) {
            is DogDetailsEvents.GetDog -> {
                getDogDetails(event.dogId)
                currentState
            }

            is DogDetailsEvents.UpdateLoadingState -> {
                currentState.copy(progressBarState = if (event.isLoading) ProgressBarState.Loading else ProgressBarState.Idle)
            }

            is DogDetailsEvents.UpdateDogState -> {
                currentState.copy(dog = event.dog)
            }

            else -> {
                currentState
            }
        }

    private fun getDogDetails(dogId: Int) {
        fetchDogDetailsUseCase(dogId).onEach { dogResource ->
            when (dogResource) {
                is Resource.Loading -> {
                    processEvent(DogDetailsEvents.UpdateLoadingState(isLoading = dogResource.isLoading))
                }

                is Resource.Success -> {
                    dogResource.data?.let {
                        processEvent(DogDetailsEvents.UpdateDogState(dog = it))
                    }
                }

                is Resource.Error -> {
                    dogResource.message?.let {
                        sendEffect(DogDetailsEffects.ShowSnackBar(it))
                    }
                }

                else -> {}
            }
        }.flowOn(dispatcherProvider.io).launchIn(viewModelScope)
    }
}