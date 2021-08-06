package com.example.rovie.data.repository

import com.example.rovie.data.api.ApiService
import com.example.rovie.data.models.Movie
import com.example.rovie.data.models.MovieDetails

class MovieRemoteSource(private val apiService: ApiService) {

    suspend fun fetchLatestMovies(): List<Movie> {
        return apiService.getLatestMovies().movies
    }

    suspend fun fetchMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId)
    }
}