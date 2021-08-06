package com.example.rovie.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.rovie.data.models.Movie
import com.example.rovie.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(private val onItemClickListener: (Movie) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(MovieDiffCallback) {

    class ViewHolder(
        private val binding: ItemMovieBinding,
        private val onItemClickListener: (Movie) -> Unit
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.root.setOnClickListener {
                onItemClickListener(movie)
            }
            binding.title.text = movie.title
            binding.genre.text = movie.genres.toString()
            Picasso.get()
                .load(movie.poster)
                .into(binding.cover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onItemClickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

object MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem.id == newItem.id
    }
}