package app.exam.imageloader.presentation

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.exam.imageloader.presentation.ui.theme.ImageLoaderTheme
import app.exam.imageloader.presentation.viewmodel.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageLoaderTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    val viewModel: ImageViewModel = hiltViewModel()
//                    viewModel.getImageData()
//                    Log.i("MainActivity_abc", "${viewModel.imageData}")
                    MyScreen()
                }
            }
        }
    }
}

@Composable
fun MyScreen(viewModel: ImageViewModel = hiltViewModel()) {

    val imagesUrl = viewModel.imageData.collectAsState()
    Log.i("MainActivity_abc", "${imagesUrl.value}")

    if(imagesUrl.value.isEmpty()) return

    val url = imagesUrl.value[1] ?: ""

    val scrollState = rememberScrollState()

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val imageLoader = remember { ImageLoader(context) }
    var bitmap by remember { mutableStateOf<Bitmap?>(null) }
    // Start loading the image
    LaunchedEffect(url) {
        coroutineScope.launch(Dispatchers.IO) {
            val job = launch {
                bitmap = imageLoader.loadImage(url)
            }
            job.join()
            launch {
                bitmap = imageLoader.loadImage(url)
            }
            launch {
                bitmap = imageLoader.loadImage(url)
            }
            launch {
                bitmap = imageLoader.loadImage(url)
            }
            launch {
                bitmap = imageLoader.loadImage(url)
            }
        }
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .verticalScroll(scrollState)) {
        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )


        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )


        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )

        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )



        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )


        ImageLoaderView(
            bitmap = bitmap,
            modifier = Modifier
                .size(200.dp)
                .align(Alignment.CenterHorizontally)
        )


    }
}
