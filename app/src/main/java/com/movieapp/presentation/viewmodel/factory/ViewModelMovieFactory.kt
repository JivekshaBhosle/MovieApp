package com.movieapp.presentation.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movieapp.data.repository.RepositoryMoviesImpl
import com.movieapp.presentation.viewmodel.ViewModelMovie

class ViewModelMovieFactory : ViewModelProvider.Factory {

    @Throws(IllegalStateException::class)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelMovie::class.java)) {
            return ViewModelMovie(repository = RepositoryMoviesImpl()) as T
        }

        throw IllegalStateException("Cannot assign ViewModel")
    }
}