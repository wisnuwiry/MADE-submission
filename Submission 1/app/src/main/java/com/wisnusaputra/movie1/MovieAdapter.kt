package com.wisnusaputra.movie1

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var movies = arrayListOf<Movie>()

    private inner class ViewHolder internal constructor(private val view: View) {
        internal fun bind(movie: Movie) {
            with(view) {
                name_movie.text = movie.name
                desc_movie.text = movie.desc
                img_movie.setImageResource(movie.photo)
            }

        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var itemView = convertView
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        }

        val viewHolder = ViewHolder(itemView as View)
        val movie = getItem(position) as Movie
        viewHolder.bind(movie)
        return itemView
    }

    override fun getItem(position: Int): Any = movies[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getCount(): Int = movies.size
}