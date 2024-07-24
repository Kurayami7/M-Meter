package com.coderops.viewmodel.tv_shows

import com.coderops.bases.BaseInteractionListener

interface TVShowsListener : BaseInteractionListener {
    fun onClickTVShows(tvId: Int)
    fun scrollToTopScreen()
    fun showOnTheAiringTVShowsResult()
    fun showAiringTodayTVShowsResult()
    fun showTopRatedTVShowsResult()
    fun showPopularTVShowsResult()
}