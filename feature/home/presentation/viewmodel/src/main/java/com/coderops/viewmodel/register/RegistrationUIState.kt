package com.coderops.viewmodel.register

data class RegistrationUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null,
    val registrationSuccess: Boolean = false

)