package com.compose.api.task.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.compose.api.task.domain.models.Medicine
import com.compose.api.task.ui.home.HomeScreen
import com.compose.api.task.ui.home.MedicineDetailScreen
import com.compose.api.task.ui.login.LoginScreen
import com.google.gson.Gson

@Composable
fun SetUpNavGraph(
    startDestination: String,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = startDestination) {
        loginRoute(onLoginSuccess = { userName->
            navController.navigate("${Screens.Home.route}/$userName") {
                popUpTo(Screens.Login.route) {
                    inclusive = true
                }
            }
        })

        homeRoute(onCardClick = {
            val medicine = Gson().toJson(it)
            navController.navigate(Screens.MedicalDetails.route + "/${Uri.encode(medicine)}")
        }, onLogout = {
            navController.navigate(Screens.Login.route) {
                popUpTo(0) {
                    inclusive = true
                }
                launchSingleTop = true
            }
        })

        medicineDetailRoute(onBackPress = {
            navController.popBackStack()
        })
    }

}

fun NavGraphBuilder.loginRoute(
    onLoginSuccess: (String) -> Unit
) {
    composable(route = Screens.Login.route) {
        LoginScreen(onLoginSuccess = onLoginSuccess)
    }
}

fun NavGraphBuilder.homeRoute(onCardClick: (Medicine) -> Unit, onLogout: ()-> Unit) {
    composable(route = Screens.Home.route + "/{userName}") {
        val userName = it.arguments?.getString("userName") ?: "Test"
        HomeScreen(userName = userName, onCardClick = onCardClick, onLogout)
    }
}

fun NavGraphBuilder.medicineDetailRoute(onBackPress: () -> Unit){
    composable(route = Screens.MedicalDetails.route + "/{medicine}"){
        val arguments = it.arguments?.getString("medicine")
        val medicine = Gson().fromJson(arguments, Medicine::class.java)
        MedicineDetailScreen(medicine = medicine, onBackPress = onBackPress)
    }
}
