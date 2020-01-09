package com.wisnu.movieapp.ui.favorite


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.wisnu.movieapp.R
import com.wisnu.movieapp.adapter.MovieAdapter
import com.wisnu.movieapp.adapter.TabAdapter
import com.wisnu.movieapp.model.Movie
import com.wisnu.movieapp.ui.detail.MovieDetailActivity
import com.wisnu.movieapp.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite_main.*
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class FavoriteMainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()
        vp_favorite.adapter =
            TabAdapter((activity as AppCompatActivity).applicationContext, childFragmentManager)
        tab_favorite.setupWithViewPager(vp_favorite)
    }
}
