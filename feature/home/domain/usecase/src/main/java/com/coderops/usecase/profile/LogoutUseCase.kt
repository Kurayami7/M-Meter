package com.coderops.usecase.profile

import com.coderops.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Boolean {
        return try {
            authRepository.logout()
            false
        } catch (_: Throwable) {
            true
        }
    }
}