package com.wisnu.movieapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.wisnu.movieapp.R
import com.wisnu.movieapp.ui.favorite.FavoriteMainFragment
import com.wisnu.movieapp.ui.home.fragment.MovieFragment
import com.wisnu.movieapp.ui.home.fragment.TVShowFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.frame_main,
                MovieFragment(),
                MovieFragment::class.java.simpleName
            ).commit()
        }

        supportActionBar?.elevation = 0f

        bottom_nav.setOnNavigationItemSelectedListener(navSelect)
    }

    private val navSelect = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.nav_movie -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frame_main,
                    MovieFragment(),
                    MovieFragment::class.java.simpleName
                ).commit()
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_tv -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frame_main,
                    TVShowFragment(),
                    TVShowFragment::class.java.simpleName
                ).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.nav_favorite -> {
                supportFragmentManager.beginTransaction().replace(
                    R.id.frame_main,
                    FavoriteMainFragment(),
                    FavoriteMainFragment::class.java.simpleName
                ).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.setting, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.ac_setting) {
            val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(intent)
        }

        return super.onOptionsItemSelected(item)
    }
}
