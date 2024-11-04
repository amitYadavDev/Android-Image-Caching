package app.exam.imageloader.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ImageDatabaseService : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}