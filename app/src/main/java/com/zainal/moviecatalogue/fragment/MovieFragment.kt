package com.zainal.moviecatalogue.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainal.moviecatalogue.DetailActivity
import com.zainal.moviecatalogue.R
import com.zainal.moviecatalogue.viewModel.MovieViewModel
import com.zainal.moviecatalogue.adapter.MovieAdapter
import com.zainal.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass.
 */
class MovieFragment : Fragment() {

    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        showMovie()
    }

    private fun showMovie() {
        rv_movie.layoutManager = LinearLayoutManager(context)
        val movieAdapter = MovieAdapter()
        movieAdapter.notifyDataSetChanged()
        rv_movie.adapter = movieAdapter

        movieViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MovieViewModel::class.java)
        movieViewModel.setMovie()

        movieViewModel.getMovie().observe(viewLifecycleOwner, Observer { movieItems ->
            if (movieItems != null) {
                movieAdapter.setData(movieItems)
            }
            showLoading(false)
        })

        movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movie) {
                val detailMovie = Intent(activity, DetailActivity::class.java)
                detailMovie.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(detailMovie)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_movie.visibility = View.VISIBLE
        } else {
            pb_movie.visibility = View.GONE
        }
    }
}
