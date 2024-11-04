package app.exam.imageloader.repository

import app.exam.imageloader.data.ImageApiService
import app.exam.imageloader.data.model.ImageModel
import app.exam.imageloader.data.model.ImageModelItem
import app.exam.imageloader.data.model.Thumbnail
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val imageApiService: ImageApiService
) {
    suspend fun getImage(): List<ImageModelItem> {
        return imageApiService.getImage(100)
    }
}