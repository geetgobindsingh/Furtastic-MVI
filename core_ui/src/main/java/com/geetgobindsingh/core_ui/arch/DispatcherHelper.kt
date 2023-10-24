package com.geetgobindsingh.core_ui.arch

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


@Suppress("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit) {
    val flow = this
    LaunchedEffect(key1 = flow) {
        flow.collectLatest(function)
    }
}

