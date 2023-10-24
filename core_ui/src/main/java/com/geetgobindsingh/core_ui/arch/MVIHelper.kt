package com.geetgobindsingh.core_ui.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow

data class StateEffectDispatch<State, Effect, Dispatch>(
    val state: State,
    val effectFlow: Flow<Effect>,
    val dispatch: (Dispatch) -> Unit,
)

@Composable
inline fun <reified State : MVIViewState, reified Effect : MVIEffect, reified Event : MVIEvent> use(
    viewModel: MVIViewModel<State, Effect, Event>,
): StateEffectDispatch<State, Effect, Event> {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val dispatch: (Event) -> Unit = { event ->
        viewModel.processEvent(event)
    }
    return StateEffectDispatch(
        dispatch = dispatch,
        effectFlow = viewModel.effects,
        state = state,
    )
}