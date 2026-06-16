package com.example.weektwo.navigation

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weektwo.ui.StudentViewModel
import com.example.weektwo.ui.screens.DashboardScreen
import com.example.weektwo.ui.screens.LoginScreen
import com.example.weektwo.ui.screens.RecordsListScreen
import com.example.weektwo.ui.screens.RegistrationScreen
import com.example.weektwo.model.Student

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: StudentViewModel = viewModel()
    var editingStudent by remember { mutableStateOf<Student?>(null) }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("dashboard") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }
        composable("dashboard") {
            DashboardScreen(
                onNavigateToRegistration = { 
                    editingStudent = null
                    navController.navigate("registration") 
                },
                onNavigateToRecords = { navController.navigate("records") },
                onLogout = {
                    navController.navigate("login") {
                        popUpTo("dashboard") { inclusive = true }
                    }
                }
            )
        }
        composable("registration") {
            RegistrationScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                studentToEdit = editingStudent
            )
        }
        composable("records") {
            RecordsListScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onEditStudent = { student ->
                    editingStudent = student
                    navController.navigate("registration")
                }
            )
        }
    }
}
