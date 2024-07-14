package com.chocolatecake.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.chocolatecake.bases.BaseViewModel
import com.chocolatecake.bases.StringsRes
import com.chocolatecake.usecase.LoginError
import com.chocolatecake.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.tasks.await
import com.chocolatecake.bases.NavigationRes as NavigationRes1

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val stringsRes: StringsRes,
    private val navigationRes: NavigationRes1,
) : BaseViewModel<LoginUiState, LoginUiEvent>(LoginUiState()) {

    private val email = MutableStateFlow("")
    private val password = MutableStateFlow("")
    private val confirmPassword = MutableStateFlow("")
    private lateinit var auth: FirebaseAuth

    init {
        viewModelScope.launch { state.collectLatest { it.log() } }
    }

/*    // Migrated to RegistrationViewModel.kt
    fun onClickSignUp() {
        sendEvent(LoginUiEvent.SignUpEvent)
    }*/

    fun login() {
        viewModelScope.launch {
            val userName = _state.value.userName
            val password = _state.value.password
            _state.update { it.copy(isLoading = true) }
            val loginResult = loginUseCase(userName, password)
            _state.update { it.copy(isLoading = false) }

            when (loginResult) {
                LoginError.USER_NAME_ERROR -> updateStateToUserNameError()
                LoginError.PASSWORD_ERROR -> updateStateToPasswordError()
                LoginError.REQUEST_ERROR -> updateStateToRequestError()
                LoginError.SUCCESS -> updateStateToSuccessLogin()
                LoginError.NO_INPUT_ERRORS -> {
                    _state.update {
                        it.copy(
                            userNameError = null,
                            passwordError = null,
                            isLoading = false
                        )
                    }
                }
                // Handle the new error types
                LoginError.INVALID_CREDENTIALS -> sendEvent(LoginUiEvent.ShowSnackBar(stringsRes.invalidCredentials))
                LoginError.INVALID_USER -> sendEvent(LoginUiEvent.ShowSnackBar(stringsRes.invalidUser))
                LoginError.USER_ALREADY_EXISTS -> sendEvent(LoginUiEvent.ShowSnackBar(stringsRes.userAlreadyExists))
            }
        }
    }

    fun onClickSignUp() {
        navigationRes.homeFeatureLink.log()
        sendEvent(LoginUiEvent.SignUpEvent(navigationRes.homeFeatureLink))
    }



    fun onClickRegister() {
        Log.d("RegistrationViewModel", "onClickRegister called") // Log entry point
        viewModelScope.launch {
            // 1. Validating input (checking for empty fields, password match, etc.)
            if (!isValidInput()) {
                Log.w("RegistrationViewModel", "Invalid input")
                _state.value = LoginUiState(passwordError = "Invalid input")
                return@launch
            }

            Log.d("RegistrationViewModel", "Input is valid, starting registration")
            // 2. Setting loading state
            _state.value = LoginUiState(isLoading = true)

            // 3. Performing registration (communicate with Firebase Authentication)
            try {
                auth = Firebase.auth
                val result = auth.createUserWithEmailAndPassword(email.value, password.value).await()

                Log.d("RegistrationViewModel", "Registration successful") //

                // Update the state to indicate successful registration
                _state.value = LoginUiState(registrationSuccess = true)

            } catch (e: Exception) {
                // Handle registration errors (e.g., email already in use, weak password)
                Log.e("RegistrationViewModel", "Registration failed: ${e.message}", e)
                _state.value = LoginUiState(passwordError = e.message, isLoading = false)
            }
        }
    }

    private fun updateStateToRequestError() {
        sendEvent(LoginUiEvent.ShowSnackBar(stringsRes.theRequestFailed))
    }

    private fun updateStateToUserNameError() {
        _state.update { it.copy(userNameError = stringsRes.usernameIsRequired, isLoading = false) }
    }

    private fun updateStateToPasswordError() {
        _state.update { it.copy(passwordError = stringsRes.passwordIsRequired, isLoading = false) }
    }

    private fun updateStateToSuccessLogin() {
        _state.update { it.copy(userNameError = null, passwordError = null, isLoading = false) }
        navigationRes.homeFeatureLink.log()
        sendEvent(LoginUiEvent.NavigateToHomeScreen(navigationRes.homeFeatureLink))
    }

    private fun isValidInput(): Boolean {
        // Need to add more validation rules here (email format, password strength, etc.)
        return email.value.isNotBlank() &&
                password.value.isNotBlank() &&
                password.value == confirmPassword.value
    }
}

fun Any.log() {
    Log.e("TAGTAG", "log(${this::class.java.simpleName}) : $this")
}