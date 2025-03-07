package com.coderops.viewmodel

sealed interface LoginUiEvent {

    data class NavigateToHomeScreen(val id: Int) : LoginUiEvent

    data class ShowSnackBar(val message: String) : LoginUiEvent

    data class SignUpEvent (val destinationID: Int) : LoginUiEvent
}