package com.geetgobindsingh.core_ui.arch

abstract class BaseViewModel<State : MVIViewState, Effect : MVIEffect, Event : MVIEvent>(
    val dispatcherProvider: DispatcherProvider,
    initialState: State
) : MVIViewModel<State, Effect, Event>(initialState) {

}