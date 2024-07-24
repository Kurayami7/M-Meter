package com.coderops.ui.my_rated

import androidx.recyclerview.widget.DiffUtil
import com.coderops.bases.BasePagingAdapter
import com.coderops.ui.home.R
import com.coderops.ui.home.databinding.ItemMovieHorizontalBinding
import com.coderops.viewmodel.common.model.MovieHorizontalUIState
import com.coderops.viewmodel.my_rated.MyRatedListner

class MyRateAdapter(listener: MyRatedListner) :
    BasePagingAdapter<MovieHorizontalUIState, ItemMovieHorizontalBinding>(Comparator, listener) {

    override val layoutId = R.layout.item_movie_horizontal

    object Comparator : DiffUtil.ItemCallback<MovieHorizontalUIState>() {
        override fun areItemsTheSame(oldItem: MovieHorizontalUIState, newItem: MovieHorizontalUIState): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieHorizontalUIState,
            newItem: MovieHorizontalUIState
        ): Boolean {
            return oldItem == newItem
        }
    }
}

