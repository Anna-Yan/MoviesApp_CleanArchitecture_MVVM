package com.annazaqaryan.moviesbyaz.presentation.movies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.annazaqaryan.moviesbyaz.domain.model.Movie
import com.annazaqaryan.moviesbyaz.presentation.movies.viewHolder.MovieViewHolder
import com.annazaqaryan.moviesbyaz.presentation.movies.viewHolder.NetworkStateViewHolder
import com.annazaqaryan.moviesbyaz.R
import com.annazaqaryan.moviesbyaz.databinding.ItemMovieBinding
import com.annazaqaryan.moviesbyaz.databinding.ItemNetworkStateBinding
import com.annazaqaryan.moviesbyaz.util.NetworkState


class MovieListAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val VIEW_TYPE_MOVIE = 1
    val VIEW_TYPE_NETWORK = 2
    var movieList: MutableList<Movie>? = ArrayList()
    private var networkState: NetworkState? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view: View

        if (viewType == VIEW_TYPE_MOVIE) {
            view = layoutInflater.inflate(R.layout.item_movie, parent, false)
            val binding = ItemMovieBinding.bind(view)
            return MovieViewHolder(binding)
        } else {
            view = layoutInflater.inflate(R.layout.item_network_state, parent, false)
            val binding = ItemNetworkStateBinding.bind(view)
            return NetworkStateViewHolder(binding)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == VIEW_TYPE_MOVIE) {
            val list = movieList?.get(position)
            (holder as MovieViewHolder).bind(list,context)
        }
        else {
            (holder as NetworkStateViewHolder).bind(networkState)
        }
    }

    private fun hasExtraRow(): Boolean {
        return networkState != null
                && networkState !=
                NetworkState.FINISHED
    }

    override fun getItemCount(): Int {
        return movieList!!.size + if (hasExtraRow()) 1 else 0
    }

    override fun getItemViewType(position: Int): Int {
        return if (hasExtraRow() && position == itemCount - 1) {
            VIEW_TYPE_NETWORK
        } else {
            VIEW_TYPE_MOVIE
        }
    }

    fun setNetworkState(newNetworkState: NetworkState) {
        val previousState = this.networkState
        val hadExtraRow = hasExtraRow()
        this.networkState = newNetworkState
        val hasExtraRow = hasExtraRow()

        if (hadExtraRow != hasExtraRow) {
            if (hadExtraRow) {
                notifyItemRemoved(movieList!!.size)
            } else {
                notifyItemInserted(movieList!!.size)
            }
        } else if (hasExtraRow && previousState != newNetworkState) {
            notifyItemChanged(itemCount - 1)
        }

    }

    fun submitList(it: List<Movie>?) {
        movieList?.clear()
        it?.let { it1 -> movieList?.addAll(it1) }
    }
}