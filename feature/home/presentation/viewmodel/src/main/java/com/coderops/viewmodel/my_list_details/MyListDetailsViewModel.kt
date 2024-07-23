package com.coderops.viewmodel.my_list_details


import androidx.lifecycle.SavedStateHandle
import com.coderops.bases.BaseViewModel
import com.coderops.bases.ListName
import com.coderops.bases.ListType
import com.coderops.bases.StringsRes
import com.coderops.entities.StatusEntity
import com.coderops.repository.NoNetworkThrowable
import com.coderops.usecase.movie_details.AddToFavouriteUseCase
import com.coderops.usecase.movie_details.AddToWatchList
import com.coderops.usecase.myList.DeleteMovieFromDetailsListUseCase
import com.coderops.usecase.myList.GetMyFavoriteListUseCase
import com.coderops.usecase.myList.GetMyListDetailsByListIdUseCase
import com.coderops.usecase.myList.GetMyWatchlistListUseCase
import com.coderops.viewmodel.my_list_details.mapper.MyListDetailsUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class MyListDetailsViewModel @Inject constructor(
    private val stringsRes: StringsRes,
    private val getFavoriteUseCase: GetMyFavoriteListUseCase,
    private val getWatchlistUseCase: GetMyWatchlistListUseCase,
    private val getMovieListDetailsUseCase: GetMyListDetailsByListIdUseCase,
    private val deleteFavoriteUseCase: AddToFavouriteUseCase,
    private val deleteMovieFromDetailsListUseCase: DeleteMovieFromDetailsListUseCase,
    private val deleteWatchlistUseCase: AddToWatchList,
    private val myListDetailsUiMapper: MyListDetailsUiMapper,
    private val savedStateHandle: SavedStateHandle,
) : BaseViewModel<MyListDetailsUiState, MyListDetailsUiEvent>(MyListDetailsUiState()),
    MyListDetailsListener {

    private val listType = savedStateHandle.get<String>("listType") ?: ""
    private val _listName = savedStateHandle.get<String>("listName") ?: ""
    val listName = when (_listName) {
        "watchlist" -> stringsRes.watchlist
        "favorite" -> stringsRes.favourite
        else -> _listName
    }
    private val listId = savedStateHandle.get<Int>("listId") ?: 0

    init {
        getData()
    }

    fun getData() {
        when (_listName) {
            ListName.favorite.name -> {
                getAllFavorite()
            }

            ListName.watchlist.name -> {
                getAllWatchlist()
            }

            else -> {
                getAllMovieListDetails(listId)
            }
        }
    }

    private fun getAllFavorite() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getFavoriteUseCase().map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onError,
        )
    }

    private fun getAllWatchlist() {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getWatchlistUseCase().map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onError,
        )
    }

    private fun getAllMovieListDetails(listId: Int) {
        _state.update { it.copy(isLoading = true) }
        tryToExecute(
            call = { getMovieListDetailsUseCase(listId).map { myListDetailsUiMapper.map(it) } },
            onSuccess = ::onGetAllMoviesSuccess,
            onError = ::onError,
        )
    }

    private fun onGetAllMoviesSuccess(items: List<MovieUiState>) {
        _state.update {
            it.copy(
                movies = items,
                isLoading = false,
                error = null,
            )
        }
    }


    fun deleteMedia(position: Int) {

        val mediaId = state.value.movies[position].id
        val mediaType = state.value.movies[position].mediaType

        _state.update {
            it.copy(
                isLoading = true,
            )
        }
        when (_listName) {
            ListName.favorite.name -> {
                deleteFavorite(mediaId , mediaType)
            }

            ListName.watchlist.name -> {
                deleteWatchlist(mediaId, mediaType)
            }

            else -> {
                deleteMovieFromListDetails(mediaId)
            }
        }
    }

    private fun deleteFavorite(mediaId: Int  , mediaType: String ) {
        tryToExecute(
            call = { deleteFavoriteUseCase(mediaId, mediaType, false) },
            onSuccess = ::onDeleteMediaSuccess,
            onError = ::onError,
        )
    }

    private fun deleteWatchlist(mediaId: Int , mediaType: String  ) {
        tryToExecute(
            call = { deleteWatchlistUseCase(mediaId, mediaType, false) },
            onSuccess = ::onDeleteMediaSuccess,
            onError = ::onError,
        )
    }

    private fun deleteMovieFromListDetails(mediaId: Int) {
        tryToExecute(
            call = {
                deleteMovieFromDetailsListUseCase(listId = listId, mediaId = mediaId)
            },
            onSuccess = ::onDeleteMediaSuccess,
            onError = ::onError,
        )
    }


    private fun onDeleteMediaSuccess(isDelete: StatusEntity) {
        _state.update { it.copy(isLoading = false) }
        when (_listName) {
            ListName.favorite.name -> {
                getAllFavorite()
            }

            ListName.watchlist.name -> {
                getAllWatchlist()
            }

            else -> {
                getAllMovieListDetails(listId)
            }
        }
    }


    private fun onError(throwable: Throwable) {
        if (throwable == NoNetworkThrowable()) {
            showErrorWithSnackBar(throwable.message ?: "No Network Connection")
        } else if (throwable == SocketTimeoutException()) {
            showErrorWithSnackBar(throwable.message ?: "time out!")
        }
        _state.update {
            it.copy(
                error = listOf(throwable.message ?: "No Network Connection"),
                isLoading = false
            )
        }
    }

    private fun showErrorWithSnackBar(messages: String) {
        sendEvent(MyListDetailsUiEvent.ShowSnackBar(messages))
    }


    override fun onClickItem(itemId: Int , mediaType:String) {
        when(mediaType){
             ListType.movie.name ->{
                 sendEvent(
                     MyListDetailsUiEvent.NavigateToMovieDetails(itemId)
                 )
            }
            ListType.tv.name ->{
                sendEvent(
                    MyListDetailsUiEvent.NavigateToTvDetails(itemId)
                )
            }
        }

    }

    override fun onClickBackButton() {
        sendEvent(MyListDetailsUiEvent.OnClickBack)
    }

}