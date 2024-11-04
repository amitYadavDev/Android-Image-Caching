package app.exam.imageloader.repository

import app.exam.imageloader.data.local.ImageDatabaseService
import app.exam.imageloader.data.local.ImageEntity
import app.exam.imageloader.data.remote.ImageApiService
import app.exam.imageloader.data.remote.model.ImageModelItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ImageRepository @Inject constructor(
    private val imageApiService: ImageApiService,
    private val imageDatabaseService: ImageDatabaseService
) {
    suspend fun getImage(): List<ImageModelItem> {
        return imageApiService.getImage(100)
    }

     fun getImageFromDb(): Flow<List<ImageEntity>> {
        return imageDatabaseService.imageDao().getAllLocalImages()
    }

    suspend fun saveImagesToDb(imageEntity: ImageEntity) {
        imageDatabaseService.imageDao().insertImage(imageEntity)
    }
}