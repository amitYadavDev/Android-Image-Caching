package app.exam.imageloader.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface ImageDao {
    //Insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImage(image: ImageEntity)

    //Get all the images from the DB
    @Query("SELECT * FROM imageentity")
    fun getAllLocalImages(): Flow<List<ImageEntity>>

    //Get specific Image entity by url
    @Query("SELECT * FROM imageentity WHERE url LIKE :url")
    fun getLocalImageById(url: String): Flow<ImageEntity>
}