package com.coderops.viewmodel.episode_details

import com.coderops.bases.BaseInteractionListener

interface EpisodeDetailsListener : BaseInteractionListener {
    fun clickToBack()
    fun clickToRate(episodeId: Int)
    fun clickToPlayFullScreen(videoKey: String)
}