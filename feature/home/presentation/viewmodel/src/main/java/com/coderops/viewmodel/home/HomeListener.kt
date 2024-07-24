package com.coderops.viewmodel.home

import com.coderops.bases.BaseInteractionListener

interface HomeListener : BaseInteractionListener {
    fun onClickMovieDetails(itemId: Int)

    fun onClickShowMore()
}