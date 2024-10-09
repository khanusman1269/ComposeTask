package com.compose.api.task.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.api.task.common.UiState
import com.compose.api.task.domain.use_cases.LoginResult
import com.compose.api.task.domain.use_cases.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase) : ViewModel() {
    private val _loginState = MutableStateFlow<UiState<Unit>>(UiState.Idle)
    val loginState: StateFlow<UiState<Unit>> = _loginState

    fun login(username: String, password: String) {
        _loginState.value = UiState.Loading
        viewModelScope.launch {
            val result = loginUseCase.execute(username, password)
            delay(2000)

            _loginState.value = when (result) {
                is LoginResult.Success -> UiState.Success(Unit)
                is LoginResult.Error -> UiState.Error(result.message)
            }
        }
    }
}