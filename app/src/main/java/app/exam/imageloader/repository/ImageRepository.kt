package app.exam.imageloader.repository

import app.exam.imageloader.data.remote.ImageApiService
import app.exam.imageloader.data.remote.model.ImageModelItem
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val imageApiService: ImageApiService
) {
    suspend fun getImage(): List<ImageModelItem> {
        return imageApiService.getImage(100)
    }
}