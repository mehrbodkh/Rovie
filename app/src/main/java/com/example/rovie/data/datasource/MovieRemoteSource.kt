package com.example.rovie.data.datasource

import com.example.rovie.data.api.ApiService
import com.example.rovie.data.models.Movie
import com.example.rovie.data.models.MovieDetails

class MovieRemoteSource(private val apiService: ApiService) : MovieSource {

    override suspend fun fetchLatestMovies(): List<Movie> {
        return apiService.getLatestMovies().movies
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetails {
        return apiService.getMovieDetails(movieId)
    }
}