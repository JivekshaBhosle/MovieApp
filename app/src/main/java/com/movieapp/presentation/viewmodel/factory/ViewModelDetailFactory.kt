package com.movieapp.presentation.viewmodel.factory


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.movieapp.data.repository.RepositoryMoviesImpl
import com.movieapp.presentation.viewmodel.ViewModelDetail

class ViewModelDetailFactory : ViewModelProvider.Factory {

    @Throws(IllegalStateException::class)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelDetail::class.java)) {
            return ViewModelDetail(repository = RepositoryMoviesImpl()) as T
        }

        throw IllegalStateException("Cannot assign ViewModel")
    }
}