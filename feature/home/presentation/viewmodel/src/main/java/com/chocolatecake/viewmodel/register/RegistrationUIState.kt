package com.chocolatecake.viewmodel.register

data class RegistrationUIState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val error: String? = null
)