package com.coderops.ui.tv_shows

import androidx.recyclerview.widget.DiffUtil
import com.coderops.bases.BasePagingAdapter
import com.coderops.ui.home.R
import com.coderops.ui.home.databinding.ItemTvShowBinding
import com.coderops.viewmodel.tv_shows.TVShowsListener
import com.coderops.viewmodel.tv_shows.TVShowsUI

class TVShowsAdapter(listener: TVShowsListener) :
    BasePagingAdapter<TVShowsUI, ItemTvShowBinding>(Comparator, listener) {

    override val layoutId = R.layout.item_tv_show

    object Comparator : DiffUtil.ItemCallback<TVShowsUI>() {
        override fun areItemsTheSame(oldItem: TVShowsUI, newItem: TVShowsUI): Boolean {
            return oldItem.tvId == newItem.tvId
        }

        override fun areContentsTheSame(
            oldItem: TVShowsUI,
            newItem: TVShowsUI
        ): Boolean {
            return oldItem == newItem
        }
    }
}

