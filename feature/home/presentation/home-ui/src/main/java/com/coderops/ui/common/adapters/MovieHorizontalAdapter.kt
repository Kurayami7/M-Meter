package com.coderops.ui.common.adapters

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.common.listener.MovieListener
import com.coderops.viewmodel.common.model.MovieHorizontalUIState

class MovieHorizontalAdapter(
    list: List<MovieHorizontalUIState>,
    listener: MovieListener
) : BaseAdapter<MovieHorizontalUIState>(list, listener) {
    override val layoutID = R.layout.item_movie_horizontal
}