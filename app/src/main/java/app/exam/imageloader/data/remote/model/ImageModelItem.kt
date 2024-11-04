package app.exam.imageloader.data.remote.model

data class ImageModelItem(
    val backupDetails: BackupDetails,
    val coverageURL: String,
    val description: String,
    val id: String,
    val language: String,
    val mediaType: Int,
    val publishedAt: String,
    val publishedBy: String,
    val seoSlugWithId: String,
    val thumbnail: Thumbnail,
    val title: String
)