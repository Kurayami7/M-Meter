package com.chocolatecake.usecase

import com.chocolatecake.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val auth: FirebaseAuth) {

    suspend operator fun invoke(userName: String, password: String): LoginError {
        // Basic input validation (you might have more robust checks)
        if (userName.isBlank()) return LoginError.USER_NAME_ERROR
        if (password.isBlank()) return LoginError.PASSWORD_ERROR

        return try {
            auth.signInWithEmailAndPassword(userName, password).await() // Use Firebase Auth
            LoginError.SUCCESS
        } catch (e: Exception) {
            // Handle Firebase Authentication exceptions (e.g., invalid credentials)
            LoginError.REQUEST_ERROR
        }
    }
}

enum class LoginError {
    USER_NAME_ERROR,
    PASSWORD_ERROR,
    REQUEST_ERROR,
    NO_INPUT_ERRORS,
    SUCCESS
}