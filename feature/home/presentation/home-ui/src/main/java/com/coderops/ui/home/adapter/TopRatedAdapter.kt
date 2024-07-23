package com.coderops.ui.home.adapter

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.home.HomeListener
import com.coderops.viewmodel.home.TopRatedUiState

class TopRatedAdapter(
    itemsTopRated: List<TopRatedUiState>,
    listener: HomeListener
) : BaseAdapter<TopRatedUiState>(itemsTopRated, listener) {
    override val layoutID = R.layout.home_item_top_rated

}