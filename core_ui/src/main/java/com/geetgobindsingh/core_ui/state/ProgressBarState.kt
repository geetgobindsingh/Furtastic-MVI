package com.geetgobindsingh.core_ui.state

sealed class ProgressBarState{

    object Loading: ProgressBarState()

    object Idle: ProgressBarState()
}