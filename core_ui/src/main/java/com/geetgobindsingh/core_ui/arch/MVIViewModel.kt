package com.geetgobindsingh.core_ui.arch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.actor
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

abstract class MVIViewModel<State : MVIViewState, Effect : MVIEffect, Event : MVIEvent>(
    initialState: State
) : ViewModel() {
    private val _state = MutableStateFlow(initialState)
    private val _effects = MutableSharedFlow<Effect>(Channel.UNLIMITED)
    private val _middleWareList = mutableListOf<MiddleWare<State, Event>>()

    val state: StateFlow<State> = _state.asStateFlow()
    val effects: SharedFlow<Effect> = _effects.asSharedFlow()
    val middleWareList: List<MiddleWare<State, Event>> = _middleWareList.toList()

    private val actor =
        viewModelScope.actor<Event>(capacity = Channel.UNLIMITED) {

            channel.consumeEach { event: Event ->
                processMiddleWares(_state.value, event)
                _state.value = reduce(
                    currentState = _state.value,
                    event = event
                )
            }
        }


    fun provideMiddleWares(vararg middleWares: MiddleWare<State, Event>) {
        _middleWareList.addAll(middleWares)
    }

    private fun processMiddleWares(state: State, event: Event) {
        _middleWareList.forEach { it.invoke(state, event) }
    }

    protected abstract fun reduce(
        currentState: State,
        event: Event
    ): State

    public fun processEvent(event: Event) {
        viewModelScope.launch {
            actor.trySend(event)
        }
    }

    protected fun sendEffect(effect: Effect) {
        _effects.tryEmit(effect)
    }

    override fun onCleared() {
        super.onCleared()
        actor.close()
    }

}