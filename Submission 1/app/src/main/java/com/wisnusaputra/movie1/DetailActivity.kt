package com.wisnusaputra.movie1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val movie = intent.getParcelableExtra(EXTRA_MOVIE) as Movie
        img_detail.setImageResource(movie.photo)
        tv_title_detail.text = movie.name
        tv_desc_detail.text = movie.desc
    }
}
