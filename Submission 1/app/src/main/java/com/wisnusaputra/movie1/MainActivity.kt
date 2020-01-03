package com.wisnusaputra.movie1

import android.content.Intent
import android.content.res.TypedArray
import android.os.Bundle
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private lateinit var dataName: Array<String>
    private lateinit var dataDesc: Array<String>
    private lateinit var dataPhoto: TypedArray

    private var movies = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = MovieAdapter(this)
        lv_movie.adapter = adapter

        prepare()
        init()

        lv_movie.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val movie = Movie(
                movies[position].photo,
                movies[position].name,
                movies[position].desc
            )

            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
            startActivity(intent)
        }
    }

    private fun prepare() {
        dataName = resources.getStringArray(R.array.data_name)
        dataDesc = resources.getStringArray(R.array.data_desc)
        dataPhoto = resources.obtainTypedArray(R.array.data_img)
    }

    private fun init() {
        for (position in dataName.indices) {
            val movie = Movie(
                dataPhoto.getResourceId(position, -1),
                dataName[position],
                dataDesc[position]
            )
            movies.add(movie)
        }
        adapter.movies = movies
    }
}
