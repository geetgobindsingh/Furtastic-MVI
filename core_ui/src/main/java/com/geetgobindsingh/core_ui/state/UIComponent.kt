package com.geetgobindsingh.core_ui.state

sealed class UIComponent{

    data class Dialog(
        val title: String,
        val description: String,
    ): UIComponent()

    data class SnackBar(
        val title: String
    ): UIComponent()

    data class None(
        val message: String,
    ): UIComponent()
}

sealed class UIComponentState {

    object Show: UIComponentState()

    object Hide: UIComponentState()
}

