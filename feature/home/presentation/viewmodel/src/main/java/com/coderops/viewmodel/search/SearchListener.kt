package com.coderops.viewmodel.search

import com.coderops.viewmodel.common.listener.MovieListener
import com.coderops.viewmodel.common.listener.PeopleListener

interface SearchListener:  MovieListener, PeopleListener {
    fun onClickFilter()
    fun onClickGenre(genresId: Int)
    fun onClickClear()
    fun showResultMovie()
    fun showResultTv()
    fun showResultPeople()

}