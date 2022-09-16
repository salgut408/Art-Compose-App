package com.sgut.android.artcomposeapp.repository

import com.sgut.android.artcomposeapp.ArtListResponse
import com.sgut.android.artcomposeapp.data.remote.ArtApi
import com.sgut.android.artcomposeapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class ArtworkRepository @Inject constructor(
    private val api: ArtApi
){
    suspend fun getArtworksList(): Resource<ArtListResponse> {
        val response = try {
            api.getAllArt("id,title,artist_display,id,date_start,medium_display,image_id,iiif_url", 1)
        } catch (e: Exception) {
            return Resource.Error("Error occurred")
        }
        return Resource.Success(response)
    }
}