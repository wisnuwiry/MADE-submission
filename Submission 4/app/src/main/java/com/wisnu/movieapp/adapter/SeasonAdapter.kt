package com.wisnu.movieapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wisnu.movieapp.R
import com.wisnu.movieapp.model.Season
import com.wisnu.movieapp.utils.Constant
import kotlinx.android.synthetic.main.item_season.view.*

class SeasonAdapter(private val seasons: ArrayList<Season>) :
    RecyclerView.Adapter<SeasonAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItem(season: Season) {
            with(itemView) {
                Picasso.get().load(Constant.URL_IMG + "w92/" + season.poster).placeholder(R.drawable.movie).into(img_season)
                tv_season.text = season.name
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_season, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = seasons.size

    override fun onBindViewHolder(holder: SeasonAdapter.ViewHolder, position: Int) {
        holder.bindItem(seasons[position])
    }

}