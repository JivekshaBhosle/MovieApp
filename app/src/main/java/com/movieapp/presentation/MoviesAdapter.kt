package com.movieapp.presentation

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.movieapp.R
import com.movieapp.domain.model.MovieSection
import kotlinx.android.synthetic.main.movie_list_grid_item.view.*

class MoviesAdapter(
    private var moviesSections: ArrayList<MovieSection>,
    private var onClick: (MovieSection) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context? = null

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val movieView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.movie_list_grid_item, parent, false)
        val movieViewHolder = MoviesViewHolder(movieView)
        context = parent.context
        return movieViewHolder
    }

    override fun getItemCount(): Int {
        return moviesSections.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val movieSection = moviesSections[position]

        bindImage(holder.itemView.movie_poster, String())
        holder.itemView.movie_list_container.setBackgroundColor(Color.TRANSPARENT)
        holder.itemView.movie_title.text = movieSection.title

        holder.itemView.setOnClickListener {
            onClick.invoke(moviesSections[position])
        }
    }


    private fun bindImage(moviePoster: ImageView, posterUrl: String) {
        moviePoster.setImageDrawable(null)

        moviePoster.adjustViewBounds = true

        Glide.with(moviePoster)
            .load(posterUrl)
            .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
            .placeholder(R.drawable.movie_placeholder)
            .fallback(R.drawable.movie_placeholder)
            .centerCrop()
            .into(moviePoster)

    }
}