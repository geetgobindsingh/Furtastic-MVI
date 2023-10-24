package com.geetgobindsingh.core_ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.geetgobindsingh.core_ui.state.ProgressBarState
import com.geetgobindsingh.core_ui.state.UIComponent
import com.geetgobindsingh.core_ui.util.Queue
import kotlinx.coroutines.launch

/**
 * @param queue: Dialogs
 * @param content: The content of the UI.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
inline fun DefaultScreenUI(
    snackbarHostState: SnackbarHostState,
    progressBarState: ProgressBarState = ProgressBarState.Idle,
    noinline topBar: @Composable () -> Unit = {},
    crossinline content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = topBar
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MaterialTheme.colorScheme.background)
        ) {
            content()

            if (progressBarState is ProgressBarState.Loading) {
                CircularIndeterminateProgressBar()
            }
        }
    }
}











