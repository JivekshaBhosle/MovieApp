package com.movieapp.domain.usecase

import com.movieapp.domain.model.MovieDetail
import com.movieapp.domain.repository.IRepositoryMovies
import com.movieapp.domain.usecase.base.UseCaseBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseMovieDetailsGet(
    private val movieId: String,
    private val repository: IRepositoryMovies,
    private val onCompleteListener: (details: MovieDetail) -> Unit
) : UseCaseBase() {

    override fun execute() {
        CoroutineScope(coroutineContext).launch() {
            val response = repository.getMovieDetails(movieId)
            withContext(job + Dispatchers.Main) {
                onCompleteListener.invoke(response)
            }
        }
    }

}