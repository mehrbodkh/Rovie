package com.example.rovie.presentation.moviedetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.rovie.data.models.MovieDetails
import com.example.rovie.databinding.FragmentMovieDetailBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    companion object {
        const val MOVIE_ID = "Movie ID"
    }

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MovieDetailViewModel
    private var movieId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getString(MOVIE_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MovieDetailViewModel::class.java)

        movieId?.let {
            viewModel.onMovieIdExtracted(it)
        }

        initObservers()
    }

    private fun initObservers() {
        lifecycleScope.launch {
            viewModel.detailUIState.collect {
                when (it) {
                    is DetailUIState.Loading -> showLoading()
                    is DetailUIState.Success -> showDetails(it.movieDetails)
                }
            }
        }
    }

    private fun showLoading() = with(binding) {
        progressBar.visibility = View.VISIBLE
        contentScrollView.visibility = View.GONE
    }

    private fun showDetails(movieDetails: MovieDetails) = with(binding) {
        progressBar.visibility = View.GONE
        contentScrollView.visibility = View.VISIBLE
        title.text = movieDetails.title
        genre.text = movieDetails.genres.toString()
        rating.text = movieDetails.imdb_rating.toString()
        info.text = movieDetails.plot
        Picasso.get()
            .load(movieDetails.images[0])
            .into(photo)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}