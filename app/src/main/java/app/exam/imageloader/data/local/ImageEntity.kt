package app.exam.imageloader.data.local

import android.graphics.Bitmap
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImageEntity(
    @PrimaryKey
    val url : String,
    val image: Bitmap?
)