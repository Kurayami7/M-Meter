package com.chocolatecake.viewmodel.register

sealed interface RegistrationUiEvent {
    data class ShowSnackBar(val message: String) : RegistrationUiEvent
/*    data class SignUpEvent (val destinationID: Int) : RegistrationUiEvent
    data class NavigateToHomeScreen(val id: Int) : RegistrationUiEvent
    data class ValidateEmail(val email: String) : RegistrationUiEvent*/
}