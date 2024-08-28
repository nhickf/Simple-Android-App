package com.grpcx.androidtask

import android.app.Application
import coil.ComponentRegistry
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.DebugLogger
import com.grpcx.androidtask.util.PdfPageDecoder
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication : Application(), ImageLoaderFactory {
    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .placeholder(R.drawable.image_placeholder)
            .crossfade(true)
            .error(R.drawable.image_placeholder)
            .logger(DebugLogger())
            .components(fun ComponentRegistry.Builder.() {
                add(PdfPageDecoder.Factory())
            })
            .build()
    }
}