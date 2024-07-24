package com.coderops.ui.home.adapter

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.home.HomeListener
import com.coderops.viewmodel.home.TrendingMoviesUiState

class TrendingAdapter(
    list: List<TrendingMoviesUiState>,
    listener: HomeListener
) : BaseAdapter<TrendingMoviesUiState>(list, listener) {
    override val layoutID = R.layout.home_item_trending
}