package com.example.rovie.presentation.movies

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rovie.R
import com.example.rovie.data.models.Movie
import com.example.rovie.databinding.MoviesFragmentBinding
import com.example.rovie.presentation.moviedetails.MovieDetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MoviesFragment : Fragment() {
    private var _binding: MoviesFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MoviesViewModel
    private val moviesAdapter = MoviesAdapter {
        findNavController().navigate(
            R.id.action_moviesFragment_to_movieDetailFragment,
            Bundle().apply { putString(MovieDetailFragment.MOVIE_ID, it.id.toString()) }
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(MoviesViewModel::class.java)

        initViews()
        initObservers()

        viewModel.onViewCreated()
    }

    private fun initViews() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding.moviesList) {
            adapter = moviesAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    private fun initObservers() {
        observeLatestMovies()
    }

    private fun observeLatestMovies() {
        lifecycleScope.launch {
            viewModel.moviesState.collect {
                when (it) {
                    is LatestMoviesUIState.Loading -> showLoading()
                    is LatestMoviesUIState.Failure -> showLoading()
                    is LatestMoviesUIState.Success -> showMovies(it.movies)
                }
            }
        }
    }

    private fun showLoading() {
        binding.moviesList.visibility = View.GONE
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun showMovies(movies: List<Movie>) {
        binding.progressBar.visibility = View.GONE
        binding.moviesList.visibility = View.VISIBLE
        moviesAdapter.submitList(movies)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}