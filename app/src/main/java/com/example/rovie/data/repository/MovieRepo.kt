package com.example.rovie.data.repository

import com.example.rovie.data.models.Movie
import com.example.rovie.data.models.MovieDetails
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getLatestMovies(): Flow<List<Movie>>
    suspend fun getMovieDetails(movieId: Int): MovieDetails
}