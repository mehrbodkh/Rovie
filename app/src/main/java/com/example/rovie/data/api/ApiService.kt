package com.example.rovie.data.api

import com.example.rovie.data.models.MovieDetails
import com.example.rovie.data.models.Movies
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movies")
    suspend fun getLatestMovies(@Query("page") page: Int = 1): Movies

    @GET("movies/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetails
}