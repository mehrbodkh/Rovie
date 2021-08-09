package com.example.rovie.data.repository

import com.example.rovie.data.datasource.MovieLocalSource
import com.example.rovie.data.datasource.MovieRemoteSource
import com.example.rovie.data.models.Movie
import com.example.rovie.data.models.MovieDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepoImpl(
    private val remoteSource: MovieRemoteSource,
    private val localSource: MovieLocalSource
): MovieRepo {

    override fun getLatestMovies(): Flow<List<Movie>> = flow {
        emit(localSource.fetchLatestMovies())
        emit(remoteSource.fetchLatestMovies())
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetails {
        return remoteSource.fetchMovieDetails(movieId)
    }
}