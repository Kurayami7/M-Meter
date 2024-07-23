package com.coderops.ui.home.adapter

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.home.HomeListener
import com.coderops.viewmodel.home.PopularMoviesUiState

class PopularMoviesAdapter(
    itemsPopular: List<PopularMoviesUiState>,
    listener: HomeListener
) : BaseAdapter<PopularMoviesUiState>(itemsPopular, listener) {
    override val layoutID = R.layout.home_item_popular_movies

}





