package com.geetgobindsingh.dogdetails.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.geetgobindsingh.core_ui.util.LocalSpacing
import com.geetgobindsingh.dog_domain.model.Dog

@Composable
fun DogDetails(
    dog: Dog,
    imageLoader: ImageLoader,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
    ) {
        BoxWithConstraints {

            val painter = rememberImagePainter(
                dog.image?.url ?: "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg",
                imageLoader = imageLoader
            )
            val height by remember {
                mutableFloatStateOf(
                    (maxWidth.toString().substring(
                        0, maxWidth.toString().lastIndexOf('.')
                    )
                        .toFloat() * (if (dog.image != null) (dog.image!!.height!!.toFloat() / dog.image!!.width!!.toFloat()) else 1.0.toFloat()))
                )
            }
            Image(
                modifier = Modifier
                    .width(maxWidth)
                    .height(
                        height = height.dp
                    ),
                painter = painter,
                contentDescription = dog.name,
                contentScale = ContentScale.Crop,
            )
        }

        Text(
            text = dog.name,
            style = MaterialTheme.typography.displayMedium,
            fontStyle = FontStyle.Normal,
            modifier = Modifier
                .padding(
                    LocalSpacing.current.spaceSmall
                )
                .align(Alignment.CenterHorizontally)
        )

        TitleAndAnswer(
            Modifier.align(Alignment.CenterHorizontally), "Weight:", dog.weight.metric + " Kgs"
        )
        TitleAndAnswer(
            Modifier.align(Alignment.CenterHorizontally), "Height:", dog.height.metric + " cm"
        )
        TitleAndAnswer(
            Modifier.align(Alignment.CenterHorizontally), "LifeSpan:", dog.lifeSpan
        )
        TitleAndAnswer(
            Modifier.align(Alignment.CenterHorizontally), "Breed Group:", dog.breedGroup
        )
        TitleAndAnswer(
            Modifier.align(Alignment.CenterHorizontally), "Bred For:", dog.bredFor
        )
        TitleAndAnswer(
            Modifier.align(Alignment.CenterHorizontally), "Temperament:", dog.temperament
        )

        Spacer(modifier = Modifier.height(60.dp))
    }
}

@Composable
fun TitleAndAnswer(modifier: Modifier, title: String, answer: String) {
    Row(
        modifier = modifier.padding(
            LocalSpacing.current.spaceSmall
        )
    ) {
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = if (answer.isNotEmpty()) answer else "NA",
            fontStyle = FontStyle.Italic,
            style = MaterialTheme.typography.headlineMedium,
        )

    }
}