package com.coderops.ui.home.adapter

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.home.HomeListener
import com.coderops.viewmodel.home.NowPlayingUiState


class NowPlayingAdapter(
    list: List<NowPlayingUiState>, listener: HomeListener
) : BaseAdapter<NowPlayingUiState>(list, listener) {
    override val layoutID = R.layout.home_item_now_playing
}