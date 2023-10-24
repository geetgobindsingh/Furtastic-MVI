package com.geetgobindsingh.core_ui.arch

import kotlinx.coroutines.CoroutineDispatcher

interface DispatcherProvider {
    val ui: CoroutineDispatcher
    val io: CoroutineDispatcher
    val bg: CoroutineDispatcher
}