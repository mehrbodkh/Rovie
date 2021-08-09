package com.example.rovie.data.repository

import com.example.rovie.data.models.Movie
import com.example.rovie.data.models.MovieDetails

interface MovieSource {
    suspend fun fetchLatestMovies(): List<Movie>
    suspend fun fetchMovieDetails(movieId: Int): MovieDetails
}