package com.sgut.android.artcomposeapp.artworklists

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import coil.imageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.sgut.android.artcomposeapp.data.models.ArtworkModel
import com.sgut.android.artcomposeapp.repository.ArtworkRepository
import com.sgut.android.artcomposeapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtworkListScreenViewModel @Inject constructor(
    private val repository: ArtworkRepository
) : ViewModel() {

    var artworkList = mutableStateOf<List<ArtworkModel>>(listOf())
    var loadError = mutableStateOf("")
    var isLoading = mutableStateOf(false)
    var endReached = mutableStateOf(false)

    init {
        loadArtwork()
    }

    fun loadArtwork() {
        viewModelScope.launch {
            isLoading.value = true
            val result = repository.getArtworksList()
            when (result) {
                is Resource.Success -> {
                    //TODO move to repository

                    val artEntries = result.data?.artworkList?.map { entry ->
                        ArtworkModel(
                            entry.id,
                            entry.title,
                            entry.dateStart,
                            entry.artistDisplay,
                            entry.mediumDisplay,
                            entry.imageId,
                            )
                    }
                    loadError.value = ""
                    isLoading.value = false
                    if (artEntries != null) {
                        artworkList.value = artEntries
                    }

                }
                is Resource.Error -> {
                    loadError.value = result.message!!
                    isLoading.value = false
                }
            }
        }
    }

    fun calcDominantColor (drawable: Drawable, onFinish: (Color)->Unit) {
        val bmp = (drawable as BitmapDrawable).bitmap.copy(Bitmap.Config.ARGB_8888, true)

        Palette.from(bmp).generate() { palette ->
            palette?.dominantSwatch?.rgb?.let { colorValue ->
                onFinish(Color(colorValue))
            }
        }

    }

    fun fetchColors(url: String, context: Context, onCalculated: (Color)->Unit) {
        viewModelScope.launch {
            val req = ImageRequest.Builder(context)
                .data(url)
                .allowHardware(false)
                .build()
            val result = req.context.imageLoader.execute(req)
            if (result is SuccessResult) {
                calcDominantColor(result.drawable) { color ->
                    onCalculated(color)
                }
            }
        }
    }




}