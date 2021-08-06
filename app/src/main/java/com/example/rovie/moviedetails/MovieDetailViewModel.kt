package com.example.rovie.moviedetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rovie.data.models.MovieDetails
import com.example.rovie.data.repository.MovieRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepo: MovieRepo
) : ViewModel() {

    private val _detailUIState = MutableStateFlow<DetailUIState>(DetailUIState.Loading)
    val detailUIState: StateFlow<DetailUIState> = _detailUIState

    fun onViewCreated(movieId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val details = movieRepo.getMovieDetails(movieId.toInt())
            _detailUIState.value = DetailUIState.Success(details)
        }
    }
}

sealed class DetailUIState {
    object Loading : DetailUIState()
    class Success(val movieDetails: MovieDetails) : DetailUIState()
}