package com.compose.api.task.data.repository

import com.compose.api.task.domain.repository.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor() : LoginRepository {
    override fun login(username: String, password: String): Boolean {
        return !(username.isEmpty() || password.isEmpty())
    }
}