package org.ahmad.project1app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.ahmad.project1app.ui.screens.ARScreen
import org.ahmad.project1app.ui.screens.AnimatedSplashScreen
import org.ahmad.project1app.ui.screens.GlosariumScreen
import org.ahmad.project1app.ui.screens.MenuScreen
import org.ahmad.project1app.ui.screens.ModuleScreen
import org.ahmad.project1app.ui.screens.VisualScreen


@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(route = Screen.Home.route) {
            MenuScreen(navController)
        }
        composable(route = Screen.Splash.route) {
            AnimatedSplashScreen(navController)
        }
        composable(route = Screen.Augmented.route) {
            ARScreen(navController)
        }
        composable(route = Screen.Visual.route) {
            VisualScreen(navController)
        }
        composable(route = Screen.Module.route) {
            ModuleScreen(navController)
        }
        composable(route = Screen.Glosarium.route) {
            GlosariumScreen(navController)
        }

    }
}

