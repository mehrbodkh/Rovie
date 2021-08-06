package com.example.rovie.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rovie.data.models.Movie
import com.example.rovie.data.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(private val movieRepo: MovieRepo) : ViewModel() {

    private val _moviesState = MutableStateFlow<LatestMoviesUIState>(LatestMoviesUIState.Loading)
    val moviesState: StateFlow<LatestMoviesUIState> = _moviesState

    fun onViewCreated() {
        viewModelScope.launch(Dispatchers.IO) {
            movieRepo.getLatestMovies().collect {
                if (it.isNotEmpty()) {
                    _moviesState.value = LatestMoviesUIState.Success(it)
                }
            }
        }
    }
}

sealed class LatestMoviesUIState {
    object Loading : LatestMoviesUIState()
    data class Success(val movies: List<Movie>) : LatestMoviesUIState()
    data class Failure(val message: String) : LatestMoviesUIState()
}