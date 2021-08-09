package com.example.rovie.data.datasource

import com.example.rovie.data.models.Movie
import com.example.rovie.data.models.MovieDetails
import javax.inject.Inject

class MovieLocalSource @Inject constructor() : MovieSource {

    override suspend fun fetchLatestMovies(): List<Movie> {
        return emptyList()
    }

    override suspend fun fetchMovieDetails(movieId: Int): MovieDetails {
        TODO("Not yet implemented")
    }
}