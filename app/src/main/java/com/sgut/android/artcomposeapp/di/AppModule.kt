package com.sgut.android.artcomposeapp.di

import com.sgut.android.artcomposeapp.data.remote.ArtApi
import com.sgut.android.artcomposeapp.repository.ArtworkRepository
import com.sgut.android.artcomposeapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideArtworkRepository(
        api: ArtApi
    ) = ArtworkRepository(api)

    @Singleton
    @Provides
    fun provideArtApi(): ArtApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ArtApi::class.java)
    }
}