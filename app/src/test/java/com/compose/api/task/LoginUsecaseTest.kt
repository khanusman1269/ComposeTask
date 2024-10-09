package com.compose.api.task

import com.compose.api.task.domain.repository.LoginRepository
import com.compose.api.task.domain.use_cases.LoginResult
import com.compose.api.task.domain.use_cases.LoginUseCase
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.whenever

class LoginUsecaseTest {
    private lateinit var loginUseCase: LoginUseCase
    private lateinit var loginRepository: LoginRepository

    @Before
    fun setup() {
        loginRepository = mock(LoginRepository::class.java)
        loginUseCase = LoginUseCase(loginRepository)
    }

    @Test
    fun testLoginWithEmptyUsername() {
        val result = loginUseCase.execute("", "somePassword")

        assertThat(result).isInstanceOf(LoginResult.Error::class.java)
        assertThat((result as LoginResult.Error).message).isEqualTo("Username cannot be empty")
    }

    @Test
    fun testLoginWithEmptyPassword() {
        val result = loginUseCase.execute("someUser", "")

        assertThat(result).isInstanceOf(LoginResult.Error::class.java)
        assertThat((result as LoginResult.Error).message).isEqualTo("Password cannot be empty")
    }

    @Test
    fun testLoginWithEmptyFields() {
        val result = loginUseCase.execute("", "")

        assertThat(result).isInstanceOf(LoginResult.Error::class.java)
        assertThat((result as LoginResult.Error).message).isEqualTo("Username cannot be empty")
    }

    @Test
    fun testSuccessfulLogin() {
        whenever(loginRepository.login("validUser", "validPassword")).thenReturn(true)

        val result = loginUseCase.execute("validUser", "validPassword")
        assertThat(result).isInstanceOf(LoginResult.Success::class.java)
    }
}