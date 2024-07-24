package com.coderops.ui.showMore

import androidx.recyclerview.widget.DiffUtil
import com.coderops.bases.BasePagingAdapter
import com.coderops.ui.home.R
import com.coderops.ui.home.databinding.ItemMovieHorizontalBinding
import com.coderops.viewmodel.showmore.ShowMoreListener
import com.coderops.viewmodel.showmore.ShowMoreUi

class ShowMoreAdapter(
    listener: ShowMoreListener
) :
    BasePagingAdapter<ShowMoreUi, ItemMovieHorizontalBinding>(ShowComparator, listener) {
    override val layoutId: Int = R.layout.item_show_more_horizontal

    object ShowComparator : DiffUtil.ItemCallback<ShowMoreUi>() {
        override fun areItemsTheSame(oldItem: ShowMoreUi, newItem: ShowMoreUi) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: ShowMoreUi, newItem: ShowMoreUi) =
            oldItem == newItem
    }
}