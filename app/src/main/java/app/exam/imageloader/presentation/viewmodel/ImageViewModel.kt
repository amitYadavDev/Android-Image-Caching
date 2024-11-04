package app.exam.imageloader.presentation.viewmodel

import android.content.Context
import android.graphics.Bitmap
import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.exam.imageloader.data.remote.model.ImageModel
import app.exam.imageloader.data.remote.model.Thumbnail
import app.exam.imageloader.presentation.ImageLoader
import app.exam.imageloader.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val imageRepository: ImageRepository) :
    ViewModel() {
    private var _imageData = MutableStateFlow<List<String>>(listOf())
    val imageData get() = _imageData

    lateinit var imageLoader: ImageLoader
    private val _imageMap = MutableStateFlow<MutableMap<String, Bitmap?>>(mutableMapOf())
    val imageMap: StateFlow<Map<String, Bitmap?>> get() = _imageMap

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val images = imageRepository.getImage()
                    .map { it.thumbnail.domain + "/" + it.thumbnail.basePath + "/0/" + it.thumbnail.key }
                Log.i("MainActivity_abc_h", "${_imageData.value}")
                _imageData.value = images

                for (i in images) loadImage(i)

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }

    fun initImageLoader(context: Context) {
        imageLoader = ImageLoader(context)
    }

    fun loadImage(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val bitmap = imageLoader.loadImage(url)
            _imageMap.value = _imageMap.value.toMutableMap().apply {
                put(url, bitmap)
            }
        }
    }
}