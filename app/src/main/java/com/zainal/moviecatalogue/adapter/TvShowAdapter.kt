package com.zainal.moviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.zainal.moviecatalogue.R
import com.zainal.moviecatalogue.model.Movie
import kotlinx.android.synthetic.main.item_tv_show.view.*

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.CardViewHolder>() {

    private val mData = ArrayList<Movie>()

    fun setData(items: ArrayList<Movie>) {
        mData.clear()
        mData.addAll(items)
        notifyDataSetChanged()
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): CardViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_tv_show, viewGroup, false)
        return CardViewHolder(view)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bind(mData[position])
    }

    override fun getItemCount(): Int = mData.size

    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(tvShow: Movie) {
            with(itemView) {
                Glide.with(itemView.context)
                    .load(tvShow.poster)
                    .apply(RequestOptions().override(210, 300))
                    .into(iv_show_poster)

                tv_show_title.text = tvShow.original_name
                tv_show_release.text = tvShow.release_date
                tv_show_overview.text = tvShow.overview

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(tvShow) }
            }
        }
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }
}