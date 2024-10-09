package com.compose.api.task.navigation

sealed class Screens(val route: String) {
    data object Login : Screens("login")
    data object Home : Screens("home")
    data object MedicalDetails : Screens("details")
}