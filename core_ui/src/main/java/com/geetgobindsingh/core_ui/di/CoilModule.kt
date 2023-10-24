package com.geetgobindsingh.core_ui.di

import coil.ImageLoader
import com.geetgobindsingh.furtastic.R
import org.koin.dsl.module


val CoilModule = module {
    single<ImageLoader> {
        ImageLoader.Builder(get())
            .error(R.drawable.error_image)
            .placeholder(R.drawable.white_background)
            .availableMemoryPercentage(0.25) // Don't know what is recommended?
            .crossfade(true)
            .build()
    }
}

