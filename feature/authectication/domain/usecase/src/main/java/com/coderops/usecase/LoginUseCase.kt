package com.coderops.usecase

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val auth: FirebaseAuth) {

    suspend operator fun invoke(userName: String, password: String): LoginError {
        if (userName.isBlank()) return LoginError.USER_NAME_ERROR
        if (password.isBlank()) return LoginError.PASSWORD_ERROR

        return try {
            auth.signInWithEmailAndPassword(userName, password).await()
            LoginError.SUCCESS
        } catch (e: FirebaseAuthInvalidCredentialsException) {
            LoginError.INVALID_CREDENTIALS
        } catch (e: FirebaseAuthInvalidUserException) {
            LoginError.INVALID_USER
        } catch (e: FirebaseAuthUserCollisionException) {
            LoginError.USER_ALREADY_EXISTS
        } catch (e: Exception) {
            // Log the full exception for debugging
            e.printStackTrace()
            LoginError.REQUEST_ERROR
        }
    }
}

enum class LoginError {
    USER_NAME_ERROR,
    PASSWORD_ERROR,
    REQUEST_ERROR,
    NO_INPUT_ERRORS,
    SUCCESS,
    // Adding these lines to handle specific errors
    INVALID_CREDENTIALS,
    INVALID_USER,
    USER_ALREADY_EXISTS
}