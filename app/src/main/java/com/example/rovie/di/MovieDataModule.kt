package com.example.rovie.di

import com.example.rovie.data.api.ApiService
import com.example.rovie.data.repository.MovieLocalSource
import com.example.rovie.data.repository.MovieRemoteSource
import com.example.rovie.data.repository.MovieRepo
import com.example.rovie.data.repository.MovieRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object MovieDataModule {

    @Provides
    fun provideMovieRemoteSource(apiService: ApiService): MovieRemoteSource {
        return MovieRemoteSource(apiService)
    }

    @Provides
    fun provideMovieRepository(
        localSource: MovieLocalSource,
        remoteSource: MovieRemoteSource
    ): MovieRepo {
        return MovieRepoImpl(remoteSource, localSource)
    }
}