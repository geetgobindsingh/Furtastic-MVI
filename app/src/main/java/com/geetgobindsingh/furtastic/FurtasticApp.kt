package com.geetgobindsingh.furtastic

import android.app.Application
import com.geetgobindsingh.furtastic.di.initKoin


class FurtasticApp : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}