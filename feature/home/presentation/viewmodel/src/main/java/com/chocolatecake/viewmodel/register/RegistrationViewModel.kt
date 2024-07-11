package com.chocolatecake.viewmodel.register

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.viewmodel.LoginUiEvent
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
        Log.d("RegistrationViewModel", "onClickRegister called") // Log entry point
        viewModelScope.launch {
            // 1. Validating input (check for empty fields, password match, etc.)
            if (!isValidInput()) {
                Log.w("RegistrationViewModel", "Invalid input")
                _state.value = RegistrationUIState(error = "Invalid input")
                return@launch
            }

            Log.d("RegistrationViewModel", "Input is valid, starting registration")
            // 2. Setting loading state
            _state.value = RegistrationUIState(isLoading = true)

            // 3. Performing registration (communicate with Firebase Authentication)
            try {
                val result = auth.createUserWithEmailAndPassword(email.value, password.value).await()

                Log.d("RegistrationViewModel", "Registration successful") //

                // Update the state to indicate successful registration
                _state.value = RegistrationUIState(registrationSuccess = true)

            } catch (e: Exception) {
                // Handle registration errors (e.g., email already in use, weak password)
                Log.e("RegistrationViewModel", "Registration failed: ${e.message}", e)
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