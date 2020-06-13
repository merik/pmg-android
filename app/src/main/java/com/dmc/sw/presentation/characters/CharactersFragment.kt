package com.dmc.sw.presentation.characters

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.dmc.core.domain.Movie
import com.dmc.sw.R
import com.dmc.sw.framework.SwViewModelFactory
import com.dmc.sw.presentation.MainActivity

class CharactersFragment : Fragment() {

    private var movieId: Int = 0

    companion object {
        fun newInstance() = CharactersFragment()
    }

    private lateinit var viewModel: CharactersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val safeArgs: CharactersFragmentArgs by navArgs()
        movieId = safeArgs.movieId

        return inflater.inflate(R.layout.characters_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        (activity as? MainActivity)?.supportActionBar?.setDisplayHomeAsUpEnabled(true)

        (activity as? MainActivity)?.setTitle("")

        viewModel = ViewModelProvider(this, SwViewModelFactory).get(CharactersViewModel::class.java)
        setupObservers()
        viewModel.loadMovie(movieId)

    }

    private fun setupObservers() {
        viewModel.movie.observe(viewLifecycleOwner, Observer {
            displayMovie(it)
        })
    }

    private fun displayMovie(movie: Movie) {
        (activity as? MainActivity)?.setTitle(movie.title)
        view?.findViewById<TextView>(R.id.openingCrawlTextView)?.text = movie.openingCrawl
        view?.findViewById<TextView>(R.id.directorTextView)?.text = movie.director
        view?.findViewById<TextView>(R.id.producerTextView)?.text = movie.producer
        view?.findViewById<TextView>(R.id.releaseDateTextView)?.text = movie.releaseDate
    }

}