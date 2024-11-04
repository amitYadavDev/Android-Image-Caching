package app.exam.imageloader.presentation

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.exam.imageloader.common.Utils.isNetworkConnected
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
                    val viewModel: ImageViewModel = hiltViewModel()
                    viewModel.initImageLoader(this)
//                    viewModel.getImageData()
//                    Log.i("MainActivity_abc", "${viewModel.imageData}")
                    MyScreen(viewModel, this)
                }
            }
        }
    }
}

@Composable
fun MyScreen(viewModel: ImageViewModel = hiltViewModel(), context: Context) {

    val imagesUrl by viewModel.imageData.collectAsState()

    val imagesBitmapMap by viewModel.imageMap.collectAsState()




    if (!isNetworkConnected(context)) {
        Log.i("MainActivity_abc", "data from db")
        viewModel.getImagesFromDb()
        ShowDbImages(viewModel)
    } else {
        LazyVerticalGrid(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            //Set 3 column
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(5.dp)
        ) {


            items(imagesUrl.size) {
                val url = imagesUrl[it]

                val bitmap = imagesBitmapMap[url]

                if (bitmap != null) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = "Thumbnail Image",
                        modifier = Modifier
                            .padding(5.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .aspectRatio(1f),
                        contentScale = ContentScale.Crop,
                    )
                } else {
                    viewModel.loadImage(url)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@Composable
fun ShowDbImages(viewModel: ImageViewModel) {
    val dbBitmaps by viewModel.dbBitmap.collectAsState()
    Log.i("MainActivity_abcd", "${dbBitmaps}")

    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black),
        //Set 3 column
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(5.dp)
    ) {


        items(dbBitmaps.size) {
            val bitmap = dbBitmaps[it]

            bitmap?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = "Thumbnail Image",
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .aspectRatio(1f),
                    contentScale = ContentScale.Crop,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}
