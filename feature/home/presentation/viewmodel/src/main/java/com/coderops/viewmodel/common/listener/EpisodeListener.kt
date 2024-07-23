package com.coderops.viewmodel.common.listener

import com.coderops.bases.BaseInteractionListener

interface EpisodeListener: BaseInteractionListener {
    fun onClickEpisode(id: Int)
}