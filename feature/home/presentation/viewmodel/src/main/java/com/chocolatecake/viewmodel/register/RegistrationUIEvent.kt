package com.chocolatecake.viewmodel.register

sealed class RegistrationUiEvent {
    object ClearForm : RegistrationUiEvent()
    data class ValidateEmail(val email: String) : RegistrationUiEvent()
    object ShowLoading : RegistrationUiEvent()
}