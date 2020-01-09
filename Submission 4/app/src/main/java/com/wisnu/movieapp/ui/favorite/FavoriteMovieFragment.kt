package com.wisnu.movieapp.ui.favorite


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.movieapp.R
import com.wisnu.movieapp.adapter.MovieAdapter
import com.wisnu.movieapp.helper.MovieHelper
import com.wisnu.movieapp.model.Movie
import com.wisnu.movieapp.ui.detail.MovieDetailActivity
import com.wisnu.movieapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite_movie.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMovieFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: MovieAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoading(true)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(FavoriteViewModel::class.java)

        adapter = MovieAdapter(ArrayList()) {}

        request()
        swipe_movie.setOnRefreshListener {
            swipe_movie.isRefreshing = false
            request()
        }
        rv_movie_favorite.layoutManager = LinearLayoutManager(activity)
    }

    private fun request() {
        activity?.let { MovieHelper.getInstance(it).open() }
        viewModel.setDataMovie(activity!!).observe(this, Observer { t ->
            showLoading(false)
            Log.d("HH", t.results.toString())
            t.results.let {
                if (it != null) {
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
        rv_movie_favorite.adapter = adapter
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            sm_movie_favorite.startShimmer()
            sm_movie_favorite.visibility = View.VISIBLE
        } else {
            sm_movie_favorite.stopShimmer()
            sm_movie_favorite.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { MovieHelper.getInstance(it).close() }
    }
}
