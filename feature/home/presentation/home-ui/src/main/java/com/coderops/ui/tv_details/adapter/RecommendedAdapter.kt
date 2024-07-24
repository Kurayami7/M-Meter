package com.coderops.ui.tv_details.adapter

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.common.listener.MediaListener
import com.coderops.viewmodel.common.model.MediaVerticalUIState

class RecommendedAdapter(
    itemRecommended: List<MediaVerticalUIState>,
    listener: MediaListener
) : BaseAdapter<MediaVerticalUIState>(itemRecommended, listener) {
    override val layoutID = R.layout.item_media_vertical
}