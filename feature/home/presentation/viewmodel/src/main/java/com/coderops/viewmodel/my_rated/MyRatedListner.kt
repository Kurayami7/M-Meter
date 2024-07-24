package com.coderops.viewmodel.my_rated

import com.coderops.bases.BaseInteractionListener

interface MyRatedListner :BaseInteractionListener{
    fun onBackPressed()
    fun onClickMovieChip()
    fun onClickTvShowChip()
}