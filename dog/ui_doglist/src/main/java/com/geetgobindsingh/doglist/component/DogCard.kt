package com.geetgobindsingh.doglist.component

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.geetgobindsingh.doglist.model.UIDogModel

@OptIn(ExperimentalCoilApi::class)
@Composable
fun DogCard(
    imageLoader: ImageLoader,
    dog: UIDogModel,
    modifier: Modifier = Modifier
) {
    BoxWithConstraints(modifier = modifier) {
        val painter = rememberImagePainter(
            dog.image.url,
            imageLoader = imageLoader
        )

        val height by remember {
            mutableFloatStateOf(
                (maxWidth
                    .toString()
                    .substring(
                        0,
                        maxWidth
                            .toString()
                            .lastIndexOf('.')
                    )
                    .toFloat() * (dog.image.height.toFloat() / dog.image.width.toFloat()))
            )
        }
        val toggled by remember {
            mutableStateOf(false)
        }
        val animatedPadding by animateDpAsState(
            if (toggled) {
                20.dp
            } else {
                0.dp
            },
            label = "padding"
        )
        Image(
            modifier = Modifier
                .width(maxWidth)
                .padding(animatedPadding)
                .height(
                    height = height.dp
                ),
            painter = painter,
            contentDescription = dog.name,
            contentScale = ContentScale.Crop,
        )
    }
}