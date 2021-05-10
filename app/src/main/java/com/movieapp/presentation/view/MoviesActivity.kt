package com.movieapp.presentation.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.movieapp.R
import com.movieapp.presentation.MoviesAdapter
import com.movieapp.presentation.viewmodel.state.UIState
import com.movieapp.presentation.viewmodel.ViewModelMovie
import com.movieapp.presentation.viewmodel.factory.ViewModelMovieFactory
import kotlinx.android.synthetic.main.activity_movie.*
import kotlinx.android.synthetic.main.error_view.view.*
import kotlinx.coroutines.flow.collect

class MoviesActivity : AppCompatActivity() {

    private var adapter: MoviesAdapter? = null

    private val viewModel: ViewModelMovie by viewModels {
        ViewModelMovieFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)

        val gridLayoutManager = GridLayoutManager(applicationContext, 2)
        gridLayoutManager.orientation = LinearLayoutManager.VERTICAL

        movie_rv?.layoutManager = gridLayoutManager
        movie_rv?.setHasFixedSize(true)

        observeViewModel()
        getMovies()
    }

    private fun observeViewModel() {

        lifecycleScope.launchWhenStarted {
            viewModel.moviesFlow.collect { it ->
                when (it) {
                    is UIState.LoadingUIState -> {
                        movie_rv?.showShimmerAdapter()
                    }
                    is UIState.ListResultsUIState -> {
                        movie_rv?.hideShimmerAdapter()
                        adapter =
                            MoviesAdapter(ArrayList((it).movieSections),
                                onClick = { section ->
                                    handleClickAction(
                                        section.href.split("/").last().split("{?dtg}").first()
                                    )
                                })
                        movie_rv?.adapter = adapter
                    }
                    is UIState.ErrorUIState -> {
                        movie_rv?.hideShimmerAdapter()
                        error_view_container?.visibility = View.VISIBLE
                        handleRetryAction()
                    }
                    is UIState.EmptyUIState -> {
                        movie_rv?.hideShimmerAdapter()
                        error_view_container?.visibility = View.VISIBLE
                        handleRetryAction()
                    }
                }
            }
        }
    }

    private fun getMovies() {
        viewModel.getMovies()
    }

    private fun handleClickAction(movieId: String) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("movieId", movieId)
        startActivity(intent)
    }

    private fun handleRetryAction() {
        error_view_container.error_retry_view_image.setOnClickListener {
            error_view_container?.visibility = View.GONE
            getMovies()
        }
    }

    override fun onDestroy() {
        viewModel.onViewDestroyed()
        super.onDestroy()
    }

}

