package com.coderops.ui.home.adapter

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.home.HomeListener
import com.coderops.viewmodel.home.UpComingMoviesUiState

class UpComingAdapter(
      upComingList: List<UpComingMoviesUiState>, listener: HomeListener
) : BaseAdapter<UpComingMoviesUiState>(upComingList,listener) {
    override val layoutID = R.layout.home_item_image_slider
}
