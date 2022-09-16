package com.sgut.android.artcomposeapp.data.remote

import com.sgut.android.artcomposeapp.ArtListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ArtApi {
    @GET("artworks/search")
    suspend fun getAllArt(
        @Query("fields")
        fieldTerms: String,
        @Query("page")
        pageNumber: Int = 1
    ): ArtListResponse
}