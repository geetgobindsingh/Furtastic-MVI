package com.geetgobindsingh.doglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.geetgobindsingh.core_ui.arch.collectInLaunchedEffect
import com.geetgobindsingh.core_ui.arch.use
import com.geetgobindsingh.core_ui.component.CircularIndeterminateProgressBar
import com.geetgobindsingh.core_ui.component.DefaultScreenUI
import com.geetgobindsingh.core_ui.component.MainTopAppBar
import com.geetgobindsingh.doglist.component.DogCard
import org.koin.androidx.compose.koinViewModel

@Composable
fun DogListScreen(
    imageLoader: ImageLoader,
    openDogDetails: (Int) -> Unit,
    modifier: Modifier = Modifier,
    dogListViewModel: DogListViewModel = koinViewModel()
) {
    val (state, effect, dispatch) = use(dogListViewModel)

    val snackbarHostState = remember { SnackbarHostState() }

    DefaultScreenUI(snackbarHostState = snackbarHostState,
        progressBarState = state.progressBarState,
        topBar = { MainTopAppBar("Furtastic") }) {

        effect.collectInLaunchedEffect { effect ->
            when (effect) {
                is DogListEffects.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        effect.message, duration = SnackbarDuration.Short
                    )
                }

                else -> {}
            }
        }

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2), modifier = Modifier.fillMaxSize()
        ) {
            items(state.dogs.size) { index ->
                if (index >= state.dogs.size - 1 && !state.endReached && !state.isLoading) {
                    dispatch(DogListEvents.GetDogs)
                }
                DogCard(
                    modifier = Modifier.clickable {
                        openDogDetails(state.dogs[index].id)
                    }, imageLoader = imageLoader, dog = state.dogs[index]
                )
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                if (state.isLoading && !state.endReached) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        CircularIndeterminateProgressBar()
                    }
                }
            }

        }
    }
}