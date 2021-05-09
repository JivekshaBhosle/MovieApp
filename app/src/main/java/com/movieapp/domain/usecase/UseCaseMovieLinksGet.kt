package com.movieapp.domain.usecase

import com.movieapp.domain.model.response.BaseMovieResponse
import com.movieapp.domain.repository.IRepositoryMovies
import com.movieapp.domain.usecase.base.UseCaseBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UseCaseMovieLinksGet(
    private val repository: IRepositoryMovies,
    private val onCompleteListener: (links: BaseMovieResponse) -> Unit
) : UseCaseBase() {

    override fun execute() {
        CoroutineScope(coroutineContext).launch() {
            val response = repository.getMovieLinks()
            withContext(job + Main) {
                onCompleteListener.invoke(response)
            }
        }
    }

}