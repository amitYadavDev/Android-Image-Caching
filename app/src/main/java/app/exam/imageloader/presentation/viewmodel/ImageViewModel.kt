package app.exam.imageloader.presentation.viewmodel

import android.util.Log

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.exam.imageloader.data.model.ImageModel
import app.exam.imageloader.data.model.Thumbnail
import app.exam.imageloader.repository.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val imageRepository: ImageRepository) :
    ViewModel() {
    private var _imageData = MutableStateFlow<List<String>>(listOf())
    val imageData get() = _imageData

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _imageData.value = imageRepository.getImage()
                    .map { it.thumbnail.domain + "/" + it.thumbnail.basePath + "/0/" + it.thumbnail.key }
                Log.i("MainActivity_abc_h", "${_imageData.value}")

            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
}