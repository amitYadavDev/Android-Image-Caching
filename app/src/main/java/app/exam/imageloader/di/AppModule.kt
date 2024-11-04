package app.exam.imageloader.di

import app.exam.imageloader.common.Utils.IMAGE_BASE_URL
import app.exam.imageloader.data.remote.ImageApiService
import app.exam.imageloader.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providesImageApiService(): ImageApiService {
        return Retrofit.Builder()
            .baseUrl(IMAGE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ImageApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesImageRepository(imageApiService: ImageApiService): ImageRepository {
        return ImageRepository(imageApiService)
    }
}