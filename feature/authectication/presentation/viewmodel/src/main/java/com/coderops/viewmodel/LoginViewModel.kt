package com.coderops.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.coderops.bases.BaseViewModel
import com.coderops.bases.StringsRes
import com.coderops.usecase.LoginError
import com.coderops.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.google.firebase.auth.FirebaseAuth
import com.coderops.bases.NavigationRes as NavigationRes1

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
        sendEvent(LoginUiEvent.SignUpEvent(navigationRes.signupFeatureLink))
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

    // Migrated to RegistrationViewModel.kt / Not in use at present
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