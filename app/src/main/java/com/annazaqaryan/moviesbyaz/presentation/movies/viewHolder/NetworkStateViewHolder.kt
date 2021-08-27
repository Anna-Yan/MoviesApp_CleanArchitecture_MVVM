package com.annazaqaryan.moviesbyaz.presentation.movies.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.annazaqaryan.moviesbyaz.databinding.ItemNetworkStateBinding
import com.annazaqaryan.moviesbyaz.util.NetworkState

class NetworkStateViewHolder (private val binding: ItemNetworkStateBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(networkState: NetworkState?) {
        if (networkState != null && networkState == NetworkState.LOADING) {
            binding.progressBarItem.visibility = View.VISIBLE;
        }
        else  {
            binding.progressBarItem.visibility = View.GONE;
        }

        if (networkState != null && networkState == NetworkState.ERROR) {
            binding.errorMsgItem.visibility = View.VISIBLE;
            binding.errorMsgItem.text = networkState.message;
        }
        else if (networkState != null && networkState == NetworkState.ENDOFLIST) {
            binding.errorMsgItem.visibility = View.VISIBLE;
            binding.errorMsgItem.text = networkState.message;
        }
        else {
            binding.errorMsgItem.visibility = View.GONE;
        }
    }
}