package com.compose.api.task.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.compose.api.task.R
import com.compose.api.task.common.UiState
import com.compose.api.task.components.CustomButton
import com.compose.api.task.components.CustomTextField
import com.compose.api.task.theme.Dark14SemiBold
import com.compose.api.task.theme.DarkSlateGrey
import com.compose.api.task.theme.White14Regular
import com.compose.api.task.theme.White18SemiBold

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit) {
    val loginViewModel = hiltViewModel<LoginViewModel>()
    val uiState by loginViewModel.loginState.collectAsState()

    LoginForm(uiState = uiState, onLoginClick = { username, password ->
        loginViewModel.login(username, password)
    }, onLoginSuccess = onLoginSuccess)

}

@Composable
fun LoginForm(
    uiState: UiState<Unit>,
    onLoginClick: (String, String) -> Unit,
    onLoginSuccess: (String) -> Unit
) {
    val username = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    var passwordVisibility by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DarkSlateGrey)
            .padding(18.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_login),
            contentDescription = "Login Image",
            modifier = Modifier
                .width(250.dp)
                .height(300.dp)
                .padding(bottom = 16.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(text = "Login", style = White18SemiBold)
        Text(text = "Please sign in to continue", style = White14Regular)

        Spacer(Modifier.height(16.dp))
        Card(
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White,
            ),
            elevation = CardDefaults.cardElevation(8.dp)
        ) {
            Column(modifier = Modifier.padding(vertical = 12.dp, horizontal = 16.dp)) {
                Text(text = "Email / User Name", style = Dark14SemiBold)
                Spacer(Modifier.height(4.dp))
                CustomTextField(
                    value = username.value,
                    onValueChange = { username.value = it },
                    placeHolderText = "Enter email or username",
                    leadingIcon = {
                        Icon(Icons.Outlined.Email, contentDescription = "Email")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )

                Spacer(Modifier.height(16.dp))
                Text(text = "Password", style = Dark14SemiBold)
                Spacer(Modifier.height(4.dp))
                CustomTextField(
                    value = password.value,
                    onValueChange = { password.value = it },
                    visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                    placeHolderText = "Enter password",
                    leadingIcon = {
                        Icon(Icons.Outlined.Lock, contentDescription = "Password")
                    },
                    trailingIcon = {
                        IconButton(onClick = {
                            passwordVisibility = !passwordVisibility
                        }) {
                            Icon(
                                painterResource(R.drawable.ic_eye),
                                contentDescription = "Password"
                            )
                        }
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                        }
                    )
                )

                when (uiState) {
                    is UiState.Success -> {
                        onLoginSuccess(username.value)
                    }

                    is UiState.Error -> {
                        Text(text = uiState.message, color = Color.Red)
                    }

                    else -> Unit
                }


                CustomButton(
                    onClick = {
                        onLoginClick(username.value, password.value)
                    },
                    buttonContent = {
                        if (uiState is UiState.Loading) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(24.dp),
                                color = Color.White,
                                strokeWidth = 2.dp
                            )
                        } else {
                            Text(text = "Login", fontSize = 18.sp)
                        }
                    })
            }
        }

    }
}
