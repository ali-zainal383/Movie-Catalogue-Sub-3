package com.zainal.moviecatalogue.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.zainal.moviecatalogue.model.Movie
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class TvViewModel : ViewModel() {

    companion object {
        private const val API_KEY = "9948fd2e03f89290b464bd94b560b078"
    }
    val listTv = MutableLiveData<ArrayList<Movie>>()

    internal fun setTv() {
        val client = AsyncHttpClient()
        val listItems = ArrayList<Movie>()
        val url = "https://api.themoviedb.org/3/discover/tv?api_key=$API_KEY&language=en-US"
        client.get(url, object : AsyncHttpResponseHandler(){
            override fun onSuccess(statusCode: Int,headers: Array<Header>?,responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")
                    for (i in 0 until list.length()) {
                        val tv = list.getJSONObject(i)
                        val tvItems = Movie()
                        tvItems.id = tv.getInt("id")
                        tvItems.original_name = tv.getString("original_name")
                        tvItems.overview = tv.getString("overview")
                        tvItems.release_date = tv.getString("first_air_date")
                        val poster = tv.getString("poster_path")
                        tvItems.poster = "https://image.tmdb.org/t/p/w154$poster"
                        listItems.add(tvItems)
                    }
                    listTv.postValue(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }
            override fun onFailure(statusCode: Int,headers: Array<Header>?,responseBody: ByteArray?,error: Throwable?) {
                Log.d("onFailure", error?.message.toString())
            }
        })
    }

    internal fun getTv() : LiveData<ArrayList<Movie>> {
        return listTv
    }
}