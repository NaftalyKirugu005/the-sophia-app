package com.example.weektwo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DashboardScreen(
    onNavigateToRegistration: () -> Unit,
    onNavigateToRecords: () -> Unit,
    onLogout: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Dashboard", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(48.dp))
        
        Button(
            onClick = onNavigateToRegistration,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("Register New Student")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Button(
            onClick = onNavigateToRecords,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("View Student Records")
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        TextButton(onClick = onLogout) {
            Text("Logout")
        }
    }
}
