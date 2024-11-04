package app.exam.imageloader.data

import app.exam.imageloader.common.Utils.IMAGE_END_POINT
import app.exam.imageloader.data.model.ImageModel
import app.exam.imageloader.data.model.ImageModelItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {
    @GET(IMAGE_END_POINT)
    suspend fun getImage(
        @Query("limit") limit: Int
    ): List<ImageModelItem>
}