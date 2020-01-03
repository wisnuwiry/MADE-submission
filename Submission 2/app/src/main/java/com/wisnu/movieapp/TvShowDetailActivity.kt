package com.wisnu.movieapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.wisnu.movieapp.model.TVShow
import kotlinx.android.synthetic.main.activity_tv_show_detail.*

class TvShowDetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_show_detail)

        val tvShow = intent.getParcelableExtra(EXTRA_TV_SHOW) as TVShow

        supportActionBar?.title = tvShow.title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvShow.poster?.let { Picasso.get().load(it).into(tv_poster) }
        tv_title.text = tvShow.title
        tv_score.text = tvShow.score
        tv_date.text = tvShow.date
        tv_overview.text = tvShow.overview
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }
}
