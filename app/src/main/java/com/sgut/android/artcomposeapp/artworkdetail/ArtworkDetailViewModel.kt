package com.sgut.android.artcomposeapp.artworkdetail

import androidx.lifecycle.ViewModel
import com.sgut.android.artcomposeapp.repository.ArtworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ArtworkDetailViewModel @Inject constructor(
    private val repository: ArtworkRepository
) : ViewModel() {
}