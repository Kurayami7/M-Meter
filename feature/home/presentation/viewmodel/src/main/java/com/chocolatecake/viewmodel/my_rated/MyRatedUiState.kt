package com.chocolatecake.viewmodel.my_rated

import androidx.paging.PagingData
import com.chocolatecake.viewmodel.common.model.MovieHorizontalUIState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class MyRatedUiState (
    val MyRateType: RateType = RateType.Movies,
    val MyRatedMedia: Flow<PagingData<MovieHorizontalUIState>> = emptyFlow(),
    val errorList: List<String>? = emptyList(),
    val isLoading: Boolean = false
)



enum class RateType{
    Movies ,
    TVShows
}
