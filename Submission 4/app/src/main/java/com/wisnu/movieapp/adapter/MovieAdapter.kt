package com.wisnu.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wisnu.movieapp.R
import com.wisnu.movieapp.model.Movie
import com.wisnu.movieapp.utils.Constant
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val movies: ArrayList<Movie>, private val listener: (Movie) -> Unit) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(movie: Movie, listener: (Movie) -> Unit) {
            with(itemView) {
                Picasso.get().load(Constant.URL_IMG + "w92/" + movie.poster).placeholder(R.drawable.movie).into(img_movie)
                tv_title_movie.text = movie.title
                tv_score_movie.text = movie.score
                tv_date_movie.text = movie.date
                tv_overview_movie.text = movie.overview
            }
            itemView.setOnClickListener {
                listener(movie)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MovieAdapter.ViewHolder, position: Int) {
        holder.bindItem(movies[position], listener)
    }

}