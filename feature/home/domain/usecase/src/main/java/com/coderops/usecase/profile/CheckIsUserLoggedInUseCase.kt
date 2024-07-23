package com.coderops.usecase.profile

import com.coderops.repository.AuthRepository
import javax.inject.Inject

class CheckIsUserLoggedInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        val loginState = authRepository.isUserLoggedIn()
        if (loginState) {
            return true
        }
        return false
    }
}