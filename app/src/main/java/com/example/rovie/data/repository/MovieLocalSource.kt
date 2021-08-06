package com.example.rovie.data.repository

import com.example.rovie.data.models.Movie
import javax.inject.Inject

class MovieLocalSource @Inject constructor() {

    suspend fun fetchLatestMovies(): List<Movie> {
        return emptyList()
    }
}