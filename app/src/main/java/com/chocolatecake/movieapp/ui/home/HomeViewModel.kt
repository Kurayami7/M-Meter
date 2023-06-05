package com.chocolatecake.movieapp.home

import androidx.lifecycle.viewModelScope
import com.chocolatecake.movieapp.data.repository.MovieRepository
import com.chocolatecake.movieapp.domain.usecases.home.GetNowPlayingUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetPopularMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetPopularPeopleUsecase
import com.chocolatecake.movieapp.domain.usecases.home.GetRecommendedUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetTopRatedUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetTrendingMoviesUseCase
import com.chocolatecake.movieapp.domain.usecases.home.GetUpcomingMoviesUseCase
import com.chocolatecake.movieapp.home.adapter.HomeListener
import com.chocolatecake.movieapp.ui.base.BaseViewModel
import com.chocolatecake.movieapp.ui.home.HomeItem
import com.chocolatecake.movieapp.ui.home.ui_state.HomeUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class HomeViewModel @Inject constructor(
    private val nowPlayingUseCase: GetNowPlayingUseCase,
    private val popularMoviesUseCase: GetPopularMoviesUseCase,
    private val popularPeopleUseCase: GetPopularPeopleUsecase,
    private val recommendedUseCase: GetRecommendedUseCase,
    private val topRatedUseCase: GetTopRatedUseCase,
    private val trendingMoviesUseCase: GetTrendingMoviesUseCase,
    private val upcomingMoviesUseCase: GetUpcomingMoviesUseCase
) :
    BaseViewModel(), HomeListener {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState

    override fun getData() {
        _uiState.update { it.copy(isLoading = true) }
        getUpComingMovies()
        getPopularPeople()


    }

    private fun getUpComingMovies() {
        viewModelScope.launch {
            upcomingMoviesUseCase().collect { upComingList ->
                _uiState.update {
                    it.copy(
                        upComingMovies = HomeItem.Slider(upComingList), isLoading = false
                    )
                }
            }
        }
    }

    private fun getPopularPeople() {
        viewModelScope.launch {
            popularPeopleUseCase().collect { popularPepleList ->
                _uiState.update {
                    it.copy(
                        popularPeople = HomeItem.PopularPeople(popularPepleList), isLoading = false
                    )
                }
            }
        }
    }

    override fun onClickNowPlaying(itemId: Int) {

    }

    override fun onClickTrending(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickPopularMovies(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickTopRated(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickRecommended(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickUpComing(itemId: Int) {
        TODO("Not yet implemented")
    }

    override fun onClickPopularPeople(itemId: Int) {
        TODO("Not yet implemented")
    }

}