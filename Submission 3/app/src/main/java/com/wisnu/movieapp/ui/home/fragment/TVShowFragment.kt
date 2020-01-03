package com.wisnu.movieapp.ui.home.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wisnu.movieapp.R
import com.wisnu.movieapp.adapter.TVShowAdapter
import com.wisnu.movieapp.model.TVShow
import com.wisnu.movieapp.ui.detail.TvShowDetailActivity
import com.wisnu.movieapp.viewmodel.TvViewModel
import kotlinx.android.synthetic.main.fragment_tvshow.*

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment() {

    private lateinit var tvViewModel: TvViewModel
    private lateinit var adapter: TVShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tvshow, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        tvViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(TvViewModel::class.java)

        rv_tv_show.layoutManager = LinearLayoutManager(activity)

        tvViewModel.setData().observe(this, Observer {t->
            t?.result.let {
                showLoading(false)
                if(it != null){
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
        rv_tv_show.adapter = adapter
    }

    private fun showLoading(loading: Boolean) {
        if (loading) {
            sm_tv.startShimmer()
            sm_tv.visibility = View.VISIBLE
        } else {
            sm_tv.stopShimmer()
            sm_tv.visibility = View.GONE
        }
    }
}
