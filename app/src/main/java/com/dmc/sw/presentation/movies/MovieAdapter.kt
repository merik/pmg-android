package com.dmc.sw.presentation.movies

import com.dmc.core.domain.Movie
import com.dmc.sw.R
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MovieAdapter(val movieClickListener: OnMovieClickListener) : RecyclerView.Adapter<MovieHolder>() {

    private var movies = listOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MovieHolder(inflater, parent)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        var movie = movies[position]
        holder.bind(movie, movieClickListener)
    }

    fun setMovies(movies: List<Movie>) {
        this.movies = movies
        this.notifyDataSetChanged()
    }
}

class MovieHolder(val inflater: LayoutInflater, val parent: ViewGroup): RecyclerView.ViewHolder(inflater.inflate(
    R.layout.movie_item, parent, false)) {
    private var mPosterImageView: ImageView? = null
    private var mTitleTextView: TextView? = null
    private var mDirectorTextView: TextView? = null

    init {
        mDirectorTextView = itemView.findViewById(R.id.directorTextView)
        mTitleTextView = itemView.findViewById(R.id.titleTextView)
        mPosterImageView = itemView.findViewById(R.id.moviePosterImageView)
    }

    fun bind(movie: Movie, clickListener: OnMovieClickListener) {
        mTitleTextView?.text = movie.title
        mDirectorTextView?.text = movie.director
        itemView.setOnClickListener {
            clickListener.onMovieClicked(movie)
        }
    }
}

interface OnMovieClickListener {
    fun onMovieClicked(movie: Movie)
}