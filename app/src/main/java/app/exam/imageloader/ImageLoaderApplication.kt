package app.exam.imageloader

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ImageLoaderApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}