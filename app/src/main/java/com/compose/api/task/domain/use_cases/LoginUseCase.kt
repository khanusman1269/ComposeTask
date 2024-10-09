package com.compose.api.task.domain.use_cases

import com.compose.api.task.domain.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val loginRepository: LoginRepository)  {

    fun execute(username: String, password: String): LoginResult {
        if (username.isBlank()) {
            return LoginResult.Error("Username cannot be empty")
        }
        if (password.isBlank()) {
            return LoginResult.Error("Password cannot be empty")
        }
        return if (loginRepository.login(username, password)) {
            LoginResult.Success
        } else {
            LoginResult.Error("Invalid username or password")
        }
    }
}

sealed class LoginResult {
    data object Success : LoginResult()
    data class Error(val message: String) : LoginResult()
}