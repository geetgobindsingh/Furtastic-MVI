package com.geetgobindsingh.furtastic.ui

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import coil.ImageLoader
import com.geetgobindsingh.core_ui.ui.theme.FurtasticTheme
import com.geetgobindsingh.furtastic.navigation.MainNavigation
import org.koin.android.ext.android.inject

class SingleActivity : ComponentActivity() {

    // https://coil-kt.github.io/coil/getting_started/#image-loaders
    val imageLoader: ImageLoader by inject<ImageLoader>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FurtasticTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(imageLoader = imageLoader)
                }
            }
        }
    }

}