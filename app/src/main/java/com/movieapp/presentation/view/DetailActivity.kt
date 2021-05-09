package com.movieapp.presentation.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.movieapp.R
import com.movieapp.domain.model.MovieDetail
import com.movieapp.presentation.viewmodel.UIDetailState
import com.movieapp.presentation.viewmodel.ViewModelDetail
import com.movieapp.presentation.viewmodel.factory.ViewModelDetailFactory
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_movie.error_view_container
import kotlinx.android.synthetic.main.error_view.view.*
import kotlinx.coroutines.flow.collect

class DetailActivity : AppCompatActivity() {

    private var movieId: String = String()

    private val viewModel: ViewModelDetail by viewModels {
        ViewModelDetailFactory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val moviesId = intent.getStringExtra("movieId")
        if (moviesId != null) {
            movieId = moviesId
        }

        observeViewModel()
        getMovieDetails()
    }

    private fun observeViewModel() {

        lifecycleScope.launchWhenStarted {
            viewModel.moviesFlow.collect { it ->
                when (it) {
                    is UIDetailState.LoadingUIState -> {
                        shimmerLayout.visibility = View.VISIBLE
                        shimmerLayout.startShimmer()
                    }
                    is UIDetailState.ResultsUIState -> {
                        renderMovieDetails(it.movieDetails)
                        shimmerLayout.stopShimmer()
                        shimmerLayout.visibility = View.GONE
                    }
                    is UIDetailState.ErrorUIState -> {
                        shimmerLayout.visibility = View.GONE
                        error_view_container?.visibility = View.VISIBLE
                        handleRetryAction()
                    }
                    is UIDetailState.EmptyUIState -> {
                        shimmerLayout.visibility = View.GONE
                        error_view_container?.visibility = View.VISIBLE
                        handleRetryAction()
                    }
                }
            }
        }
    }

    private fun getMovieDetails() {
        viewModel.getMovieDetails(movieId)
    }

    private fun renderMovieDetails(movieDetail: MovieDetail) {

        movie_detail_poster.setImageDrawable(null)

        movie_detail_poster.adjustViewBounds = true

        Glide.with(movie_detail_poster)
            .load(String())
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .placeholder(R.drawable.movie_placeholder)
            .fallback(R.drawable.movie_placeholder)
            .centerCrop()
            .into(movie_detail_poster)

        movie_detail_title.text = movieDetail.title
        movie_detail_description.text = movieDetail.description
    }

    private fun handleRetryAction() {
        error_view_container.error_retry_view_image.setOnClickListener {
            error_view_container?.visibility = View.GONE
            getMovieDetails()
        }
    }

}

