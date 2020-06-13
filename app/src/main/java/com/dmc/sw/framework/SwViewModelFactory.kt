package com.dmc.sw.framework

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

object SwViewModelFactory : ViewModelProvider.Factory {
    lateinit var application: Application
    lateinit var dependencies: Interactors

    fun inject(application: Application, dependencies: Interactors) {
        SwViewModelFactory.application = application
        SwViewModelFactory.dependencies = dependencies
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (SwViewModel::class.java.isAssignableFrom(modelClass)) {
            return modelClass.getConstructor(Application::class.java, Interactors::class.java)
                .newInstance(application, dependencies)
        } else {
            throw IllegalStateException("ViewModel must extend SwViewModel")
        }
    }

}