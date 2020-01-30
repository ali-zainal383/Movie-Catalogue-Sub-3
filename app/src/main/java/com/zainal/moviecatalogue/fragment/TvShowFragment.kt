package com.zainal.moviecatalogue.fragment


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainal.moviecatalogue.DetailActivity
import com.zainal.moviecatalogue.R
import com.zainal.moviecatalogue.adapter.TvShowAdapter
import com.zainal.moviecatalogue.model.Movie
import com.zainal.moviecatalogue.viewModel.TvViewModel
import kotlinx.android.synthetic.main.fragment_tv_show.*

/**
 * A simple [Fragment] subclass.
 */
class TvShowFragment : Fragment() {

    private lateinit var tvViewModel: TvViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading(true)
        showTv()
    }

    private fun showTv() {
        rv_tv_show.layoutManager = LinearLayoutManager(context)
        val tvShowAdapter = TvShowAdapter()
        tvShowAdapter.notifyDataSetChanged()
        rv_tv_show.adapter = tvShowAdapter

        tvViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(TvViewModel::class.java)
        tvViewModel.setTv()

        tvViewModel.getTv().observe(viewLifecycleOwner, Observer { tvItems ->
            if (tvItems != null) {
                tvShowAdapter.setData(tvItems)
            }
            showLoading(false)
        })

        tvShowAdapter.setItemClickCallback(object : TvShowAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movie) {
                val detailTvShow = Intent(activity, DetailActivity::class.java)
                detailTvShow.putExtra(DetailActivity.EXTRA_DATA, data)
                startActivity(detailTvShow)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            pb_tv_show.visibility = View.VISIBLE
        } else {
            pb_tv_show.visibility = View.GONE
        }
    }
}
