package com.dmc.sw.presentation.characters

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dmc.core.domain.Movie
import com.dmc.sw.framework.Interactors
import com.dmc.sw.framework.SwViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CharactersViewModel(application: Application, interactors: Interactors) : SwViewModel(application, interactors) {

    private var _movie = MutableLiveData<Movie>()
    val movie: LiveData<Movie>
        get() = _movie

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            val selectingMovie = interactors.getMovie(movieId)

            withContext(Dispatchers.IO) {
                _movie.postValue(selectingMovie)
            }
        }
    }

}