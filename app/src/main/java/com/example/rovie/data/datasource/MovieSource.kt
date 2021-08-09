package com.example.rovie.data.datasource

import com.example.rovie.data.models.Movie
import com.example.rovie.data.models.MovieDetails

interface MovieSource {
    suspend fun fetchLatestMovies(): List<Movie>
    suspend fun fetchMovieDetails(movieId: Int): MovieDetails
}