package com.geetgobindsingh.doglist

import com.geetgobindsingh.core_ui.arch.MVIEffect
import com.geetgobindsingh.core_ui.arch.MVIEvent
import com.geetgobindsingh.core_ui.arch.MVIViewState
import com.geetgobindsingh.core_ui.state.ProgressBarState
import com.geetgobindsingh.core_ui.state.UIComponent
import com.geetgobindsingh.dog_domain.usecase.FetchDogsUseCase
import com.geetgobindsingh.doglist.model.UIDogModel

data class DogListState(
    val progressBarState: ProgressBarState = ProgressBarState.Idle,
    val dogs: List<UIDogModel> = listOf(),
    val filteredDogs: List<UIDogModel> = listOf(),
    val dogId: Int = 0,
    val isLoading: Boolean = false,
    val endReached: Boolean = false,
) : MVIViewState


sealed class DogListEffects : MVIEffect {
    data class ShowSnackBar(val message: String) : DogListEffects()
}

sealed class DogListEvents : MVIEvent {
    object SubscribeDogs : DogListEvents()
    object GetDogs : DogListEvents()
    data class UpdateViewState(val paginationState: FetchDogsUseCase.PaginationState) :
        DogListEvents()
}