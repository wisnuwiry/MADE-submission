package com.wisnu.movieapp.ui.home.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.movieapp.R
import com.wisnu.movieapp.adapter.MovieAdapter
import com.wisnu.movieapp.model.Movie
import com.wisnu.movieapp.ui.detail.MovieDetailActivity
import com.wisnu.movieapp.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {
    private lateinit var movieViewModel: MovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(
        savedInstanceState: Bundle?
    ) {
        super.onActivityCreated(savedInstanceState)
        movieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieViewModel::class.java)

        rv_movie.layoutManager = LinearLayoutManager(activity)

        movieViewModel.setData().observe(this, Observer { t ->
            t?.results.let {
                if (it != null) {
                    showLoading(false)
                    showData(it as ArrayList<Movie>)
                }
            }
        })
    }

    private fun showData(data: ArrayList<Movie>) {
        adapter = MovieAdapter(data) {
            val intent = Intent(activity, MovieDetailActivity::class.java)
            intent.putExtra(MovieDetailActivity.EXTRA_MOVIE, it.id)
            startActivity(intent)
        }
        adapter.notifyDataSetChanged()
        rv_movie.adapter = adapter
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            sm_movie.startShimmer()
            sm_movie.visibility = View.VISIBLE
        } else {
            sm_movie.stopShimmer()
            sm_movie.visibility = View.GONE
        }
    }
}
