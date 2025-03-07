package com.coderops.viewmodel.profile

import androidx.lifecycle.viewModelScope
import com.coderops.bases.BaseViewModel
import com.coderops.bases.NavigationRes
import com.coderops.usecase.profile.CheckIsUserLoggedInUseCase
import com.coderops.usecase.profile.GetAccountDetailsUseCase
import com.coderops.usecase.profile.LogoutUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getAccountDetailsUseCase: GetAccountDetailsUseCase,
    private val logoutUseCase: LogoutUseCase,
    private val profileUiMapper: ProfileUiMapper,
    private val checkIsUserLoggedInUseCase: CheckIsUserLoggedInUseCase,
    private val navigationRes: NavigationRes,
    private val auth: FirebaseAuth // Inject FirebaseAuth

) : BaseViewModel<ProfileUIState, ProfileUiEvent>(ProfileUIState()), ProfileListener {

    init {
        getAccountDetails()

        // Add authentication state listener
        auth.addAuthStateListener { firebaseAuth ->
            val isLoggedIn = firebaseAuth.currentUser != null
            _state.update { it.copy(isLoggedIn = isLoggedIn) }
            if (isLoggedIn) {
                getAccountDetails() // Fetch user details if logged in
            }
        }
    }

    private fun getAccountDetails() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            try {
                val profileEntity = profileUiMapper.map(getAccountDetailsUseCase())
                _state.update {
                    it.copy(
                        username = profileEntity.username,
                        avatarUrl = profileEntity.avatarUrl,
                        error = null,
                        isLoggedIn = true,
                        isLoading = false
                    )
                }
            } catch (th: Throwable) {
                onError(th)
            }
        }
    }

    private fun onError(th: Throwable) {
        val errors = _state.value.error
        _state.update { it.copy(error = errors, isLoading = false) }
    }

    override fun onClickFavorite() {
        sendEvent(ProfileUiEvent.NavigateToFavoriteScreen)
    }

    override fun onClickWatchlist() {
        sendEvent(ProfileUiEvent.NavigateToWatchlistScreen)
    }

    override fun onClickWatchHistory() {
        sendEvent(ProfileUiEvent.NavigateToWatchHistoryScreen)
    }

    override fun onClickMyLists() {
        sendEvent(ProfileUiEvent.NavigateToMyListsScreen)
    }

    override fun onClickPopcornPuzzles() {
        sendEvent(ProfileUiEvent.NavigateWithLink(navigationRes.gameFeatureLink))
    }

    override fun onClickLogout() {
        sendEvent(ProfileUiEvent.Logout)
    }

    fun logout() {
        viewModelScope.launch {
            _state.update { it.copy(username = "", isLoggedIn = false) }
            auth.signOut()
            logoutUseCase()
        }
    }

    override fun onUserNotLoggedIn() {
        viewModelScope.launch {
            _state.update { it.copy(isLoggedIn = true) }
            if (checkIsUserLoggedInUseCase()) {
                _state.update {
                    it.copy(isLoggedIn = false)
                }
            }

        }
    }

    override fun ocClickLogIn() {
        sendEvent(ProfileUiEvent.NavigateWithLink(navigationRes.authFeatureLink))
    }
}