package com.wisnu.movieapp.fragment


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.wisnu.movieapp.R
import com.wisnu.movieapp.TvShowDetailActivity
import com.wisnu.movieapp.adapter.TVShowAdapter
import com.wisnu.movieapp.model.TVShow
import kotlinx.android.synthetic.main.fragment_tvshow.view.*

/**
 * A simple [Fragment] subclass.
 */
class TVShowFragment : Fragment() {

    private var tvShow = ArrayList<TVShow>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_tvshow, container, false)

        getData()
        view.rv_tv_show.layoutManager = LinearLayoutManager(activity)
        view.rv_tv_show.adapter = TVShowAdapter(tvShow) {
            val intent = Intent(activity, TvShowDetailActivity::class.java)
            intent.putExtra(TvShowDetailActivity.EXTRA_TV_SHOW, it)
            startActivity(intent)
        }
        return view
    }

    private fun getData(): ArrayList<TVShow> {
        val dataTitle = resources.getStringArray(R.array.tv_title)
        val dataScore = resources.getStringArray(R.array.tv_score)
        val dataDate = resources.getStringArray(R.array.tv_date)
        val dataOverview = resources.getStringArray(R.array.tv_overview)
        val dataPoster = resources.obtainTypedArray(R.array.tv_poster)

        for (position in dataTitle.indices) {
            val tv = TVShow(
                dataTitle[position],
                dataScore[position],
                dataDate[position],
                dataOverview[position],
                dataPoster.getResourceId(position, 0)
            )
            tvShow.add(tv)
        }

        dataPoster.recycle()
        return tvShow
    }

}
