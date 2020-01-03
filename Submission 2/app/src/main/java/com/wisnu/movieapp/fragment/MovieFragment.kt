package com.wisnu.movieapp.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.movieapp.MovieDetailActivity
import com.wisnu.movieapp.R
import com.wisnu.movieapp.adapter.MovieAdapter
import com.wisnu.movieapp.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.view.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private var movies = ArrayList<Movie>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_movie, container, false)

        getData()
        view.rv_movie.layoutManager = LinearLayoutManager(activity)
        view.rv_movie.adapter = MovieAdapter(movies) {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, it)
            startActivity(intent)
        }
        return view
    }


    private fun getData(): ArrayList<Movie> {
        val dataTitle = resources.getStringArray(R.array.movie_title)
        val dataScore = resources.getStringArray(R.array.movie_score)
        val dataDate = resources.getStringArray(R.array.movie_date)
        val dataOverview = resources.getStringArray(R.array.movie_overview)
        val dataPoster = resources.obtainTypedArray(R.array.movie_poster)

        for (position in dataTitle.indices) {
            val movie = Movie(
                dataTitle[position],
                dataScore[position],
                dataDate[position],
                dataOverview[position],
                dataPoster.getResourceId(position, 0)
            )
            movies.add(movie)
        }

        dataPoster.recycle()
        return movies
    }
}
