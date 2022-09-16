package com.sgut.android.artcomposeapp.artworklists

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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




}