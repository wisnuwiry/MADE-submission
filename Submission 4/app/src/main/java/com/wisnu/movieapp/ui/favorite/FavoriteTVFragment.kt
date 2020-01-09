package com.wisnu.movieapp.ui.favorite


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.wisnu.movieapp.R
import com.wisnu.movieapp.adapter.TVShowAdapter
import com.wisnu.movieapp.helper.TvHelper
import com.wisnu.movieapp.model.Movie
import com.wisnu.movieapp.model.TVShow
import com.wisnu.movieapp.ui.detail.TvShowDetailActivity
import com.wisnu.movieapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite_tv.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTVFragment : Fragment() {

    private lateinit var viewModel: FavoriteViewModel
    private lateinit var adapter: TVShowAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        showLoading(true)

        viewModel  = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FavoriteViewModel::class.java)
        adapter = TVShowAdapter(ArrayList()){}

        request()
        swipe_tv.setOnRefreshListener {
            swipe_tv.isRefreshing = false
            request()
        }
        rv_tv_favorite.layoutManager = LinearLayoutManager(activity)
    }

    private fun request() {
        viewModel.setDataTv(activity!!).observe(this, Observer { t ->
            showLoading(false)
            t.result.let {
                if (it != null) {
                    showData(it as ArrayList<TVShow>)
                }
            }
        })
    }

    private fun showData(data: ArrayList<TVShow>) {
        adapter = TVShowAdapter(data) {
            val intent = Intent(activity, TvShowDetailActivity::class.java)
            intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, it.id)
            startActivity(intent)
        }
        adapter.notifyDataSetChanged()
        rv_tv_favorite.adapter = adapter
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            sm_tv_favorite.startShimmer()
            sm_tv_favorite.visibility = View.VISIBLE
        } else {
            sm_tv_favorite.stopShimmer()
            sm_tv_favorite.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        activity?.let { TvHelper.getInstance(it).close() }
    }
}
