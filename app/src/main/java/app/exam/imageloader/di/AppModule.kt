package app.exam.imageloader.di

import android.content.Context
import androidx.room.Room
import app.exam.imageloader.common.Utils.IMAGE_BASE_URL
import app.exam.imageloader.data.local.ImageDatabaseService
import app.exam.imageloader.data.remote.ImageApiService
import app.exam.imageloader.repository.ImageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
    fun providesImageDbService(@ApplicationContext context: Context): ImageDatabaseService {
        return Room.databaseBuilder(context, ImageDatabaseService::class.java, "image_db").build()
    }

    @Singleton
    @Provides
    fun providesImageRepository(imageApiService: ImageApiService, imageDatabaseService: ImageDatabaseService): ImageRepository {
        return ImageRepository(imageApiService, imageDatabaseService)
    }

}