package com.mindu.app

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mindu.app.ui.screens.MainScreen
import com.mindu.app.ui.screens.WelcomeScreen
import com.mindu.app.ui.screens.CreateUserScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("create_user") { CreateUserScreen(navController) }
        composable("main") { MainScreen(navController) }
    }
}

