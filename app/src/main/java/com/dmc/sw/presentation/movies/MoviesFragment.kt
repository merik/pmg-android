package com.dmc.sw.presentation.movies

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmc.core.domain.Movie
import com.dmc.sw.R
import com.dmc.sw.framework.SwViewModelFactory
import com.dmc.sw.presentation.MainActivity
import com.dmc.sw.presentation.MainViewModel

class MoviesFragment : Fragment(), OnMovieClickListener {

    private lateinit var viewModel: MoviesViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view?.findViewById<RecyclerView>(R.id.movieRecyclerView)

        viewManager = GridLayoutManager(activity, 2)
        recyclerView.layoutManager = viewManager

        movieAdapter = MovieAdapter(this)
        recyclerView.adapter = movieAdapter


    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {

        super.onActivityCreated(savedInstanceState)

        (activity as? MainActivity)?.setTitle("MOVIES")
        (activity as? MainActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(false)

        viewModel = ViewModelProvider(this, SwViewModelFactory).get(MoviesViewModel::class.java)

        setupObservers()

        viewModel.loadMovies()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    private fun setupObservers() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAdapter.setMovies(it)

        })

    }

    override fun onMovieClicked(movie: Movie) {
        Log.d("Movies", movie.title)
        val action = MoviesFragmentDirections.actionMoviesToCharacters(movieId = movie.episodeId)
        findNavController().navigate(action)
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            MoviesFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }
}