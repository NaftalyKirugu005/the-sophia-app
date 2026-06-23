package com.example.healthapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.healthapp.ui.screens.auth.LoginScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ) {
        composable(Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = { role ->
                    val destination = when (role) {
                        "PHARMACY" -> Screen.PharmacyDashboard.route
                        "RIDER" -> Screen.RiderDashboard.route
                        else -> Screen.CustomerDashboard.route
                    }
                    navController.navigate(destination) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                onNavigateToRegister = {
                    navController.navigate(Screen.Register.route)
                }
            )
        }
        
        composable(Screen.Register.route) {
            // TODO: Create RegisterScreen
        }
        
        composable(Screen.CustomerDashboard.route) {
            com.example.healthapp.ui.screens.customer.CustomerDashboard(
                onNavigateToMedicineCatalog = { navController.navigate(Screen.MedicineCatalog.route) },
                onNavigateToSos = { navController.navigate(Screen.EmergencySos.route) },
                onNavigateToReminders = { navController.navigate(Screen.MedicationReminders.route) }
            )
        }

        composable(Screen.PharmacyDashboard.route) {
            // TODO: Create PharmacyDashboard
        }

        composable(Screen.RiderDashboard.route) {
            // TODO: Create RiderDashboard
        }
    }
}
