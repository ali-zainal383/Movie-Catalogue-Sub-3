package com.zainal.moviecatalogue

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zainal.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val data = intent.getParcelableExtra<Movie>(EXTRA_DATA)

        val poster = data?.poster
        val title = data?.original_name
        val release = data?.release_date
        val overview = data?.overview

        Glide.with(this)
            .load(poster)
            .apply(RequestOptions().override(350, 450))
            .into(iv_poster_detail)

        tv_movie_title.text = title
        tv_release_detail.text = release
        tv_overview_detail.text = overview
    }
}
