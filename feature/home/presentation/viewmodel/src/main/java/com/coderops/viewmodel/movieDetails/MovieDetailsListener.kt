package com.coderops.viewmodel.movieDetails

import com.coderops.bases.BaseInteractionListener

interface MovieDetailsListener : BaseInteractionListener {
    fun onClickPlayTrailer()
    fun onClickRate(id: Int)
    fun onClickBackButton()
    fun onClickShowMore(movieId:Int)
}