package com.geetgobindsingh.dogdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.geetgobindsingh.core_ui.arch.collectInLaunchedEffect
import com.geetgobindsingh.core_ui.arch.use
import com.geetgobindsingh.core_ui.component.DefaultScreenUI
import com.geetgobindsingh.dogdetails.component.DogDetails
import org.koin.androidx.compose.koinViewModel

@Composable
fun DogDetailsScreen(
    dogId: Int,
    onBackPress: () -> Unit,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier,
    dogDetailsViewModel: DogDetailsViewModel = koinViewModel()
) {

    val (state, effect, dispatch) = use(dogDetailsViewModel)

    val snackbarHostState = remember { SnackbarHostState() }

    effect.collectInLaunchedEffect { effect ->
        when (effect) {
            is DogDetailsEffects.ShowSnackBar -> {
                snackbarHostState.showSnackbar(
                    effect.message, duration = SnackbarDuration.Short
                )
            }

            else -> {}
        }
    }

    DefaultScreenUI(
        snackbarHostState,
        progressBarState = state.progressBarState,
    ) {
        LaunchedEffect(key1 = true) {
            dispatch(DogDetailsEvents.GetDog(dogId))
        }
        state.dog?.let { dog ->
            DogDetails(dog, imageLoader)
        }

        Row(
            modifier = modifier
                .fillMaxSize()
                .padding(top = 12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val iconModifier = Modifier
                .sizeIn(
                    maxWidth = 58.dp,
                    maxHeight = 58.dp
                )
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = CircleShape
                )

            IconButton(
                onClick = onBackPress,
                modifier = Modifier
                    .padding(start = 12.dp)
                    .then(iconModifier)
            ) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    }
}