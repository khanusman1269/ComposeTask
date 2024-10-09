package com.compose.api.task.domain.repository

fun interface LoginRepository {
    fun login(username: String, password: String): Boolean
}