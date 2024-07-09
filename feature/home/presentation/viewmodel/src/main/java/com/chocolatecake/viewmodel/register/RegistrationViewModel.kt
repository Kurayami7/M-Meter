package com.chocolatecake.viewmodel.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegistrationViewModel : BaseViewModel<RegistrationUIState, RegistrationUiEvent>(RegistrationUIState()) {

    private lateinit var auth: FirebaseAuth
    // UI State (exposed as a StateFlow for observation)

/*    private val _state = MutableStateFlow(RegistrationUIState())
    val state = _state.asStateFlow()*/

    // Input fields (backed by MutableStateFlow for two-way data binding)
    val email = MutableStateFlow("")
    val password = MutableStateFlow("")
    val confirmPassword = MutableStateFlow("")

    init {
        auth = Firebase.auth
    }

    fun onClickRegister() {
        viewModelScope.launch {
            // 1. Validating input (check for empty fields, password match, etc.)
            if (!isValidInput()) {
                _state.value = RegistrationUIState(error = "Invalid input")
                return@launch
            }

            // 2. Setting loading state
            _state.value = RegistrationUIState(isLoading = true)

            // 3. Performing registration (communicate with Firebase Authentication)
            try {
                val result = auth.createUserWithEmailAndPassword(email.value, password.value).await()
                // Registration successful - you can access the new user with result.user
                _state.value = RegistrationUIState(isSuccess = true)
            } catch (e: Exception) {
                // Handle registration errors (e.g., email already in use, weak password)
                _state.value = RegistrationUIState(error = e.message, isLoading = false)
            }
        }
    }

    private fun isValidInput(): Boolean {
        // Add more validation rules here (email format, password strength, etc.)
        return email.value.isNotBlank() &&
                password.value.isNotBlank() &&
                password.value == confirmPassword.value
    }

    private suspend fun performRegistration(email: String, password: String): Boolean {
        return true // Simulating successful registration for now
    }
}