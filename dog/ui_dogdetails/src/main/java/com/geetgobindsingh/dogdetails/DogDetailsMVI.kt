package com.geetgobindsingh.dogdetails

import com.geetgobindsingh.core_ui.arch.MVIEffect
import com.geetgobindsingh.core_ui.arch.MVIEvent
import com.geetgobindsingh.core_ui.arch.MVIViewState
import com.geetgobindsingh.core_ui.state.ProgressBarState
import com.geetgobindsingh.core_ui.state.UIComponent
import com.geetgobindsingh.core_ui.util.Queue
import com.geetgobindsingh.dog_domain.model.Dog

data class DogDetailsState(val progressBarState: ProgressBarState = ProgressBarState.Idle,
                           val dog: Dog? = null,
                           val errorQueue: Queue<UIComponent> = Queue(mutableListOf()),) : MVIViewState

sealed class DogDetailsEffects : MVIEffect {
    data class ShowSnackBar(val message: String) : DogDetailsEffects()
}

sealed class DogDetailsEvents : MVIEvent {
    data class GetDog(val dogId: Int) : DogDetailsEvents()
    data class UpdateLoadingState(val isLoading: Boolean) : DogDetailsEvents()
    data class UpdateDogState(val dog: Dog) : DogDetailsEvents()
}