package com.coderops.ui.common.adapters

import com.coderops.bases.BaseAdapter
import com.coderops.ui.home.R
import com.coderops.viewmodel.common.listener.MediaListener
import com.coderops.viewmodel.common.model.MediaVerticalUIState

class MediaVerticalAdapter(
    list: List<MediaVerticalUIState>,
    listener: MediaListener
) : BaseAdapter<MediaVerticalUIState>(list, listener) {
    override val layoutID = R.layout.item_media_vertical
}