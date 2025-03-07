package com.coderops.ui.home

import com.coderops.viewmodel.home.NowPlayingUiState
import com.coderops.viewmodel.home.PopularMoviesUiState
import com.coderops.viewmodel.home.PopularPeopleUiState
import com.coderops.viewmodel.home.TopRatedUiState
import com.coderops.viewmodel.home.TrendingMoviesUiState
import com.coderops.viewmodel.home.UpComingMoviesUiState

sealed class HomeItem(val type: HomeItemType) {

    data class Slider(val list: List<UpComingMoviesUiState>) : HomeItem(HomeItemType.SLIDER)

    data class NowPlaying(val list: List<NowPlayingUiState>) :
        HomeItem(HomeItemType.NOW_PLAYING)

    data class Trending(val list: List<TrendingMoviesUiState>) : HomeItem(HomeItemType.TRENDING)

    data class TopRated(val list: List<TopRatedUiState>) : HomeItem(HomeItemType.TOP_RATED)

    data class PopularPeople(val list: List<PopularPeopleUiState>) :
        HomeItem(HomeItemType.POPULAR_PEOPLE)

    data class PopularMovies(val list: List<PopularMoviesUiState>) :
        HomeItem(HomeItemType.POPULAR_MOVIES)

}

enum class HomeItemType { SLIDER, NOW_PLAYING, TRENDING, TOP_RATED, POPULAR_PEOPLE, POPULAR_MOVIES }
