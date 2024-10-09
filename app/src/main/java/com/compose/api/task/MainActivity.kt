package com.compose.api.task

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.compose.api.task.navigation.Screens
import com.compose.api.task.navigation.SetUpNavGraph
import com.compose.api.task.theme.ComposeTaskTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContent {
            ComposeTaskTheme {
                val navController = rememberNavController()
                Scaffold {
                    it.calculateTopPadding()
                    SetUpNavGraph(
                        startDestination = Screens.Login.route,
                        navController = navController
                    )
                }
            }
        }
    }
}
