package com.dmc.sw.presentation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dmc.core.domain.Movie
import com.dmc.sw.framework.Interactors
import com.dmc.sw.framework.SwViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(application: Application, interactors: Interactors): SwViewModel(application, interactors) {

    private var _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>>
        get() = _movies

    fun loadMovies() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _movies.postValue(interactors.getMovies())
            }
        }
    }
}