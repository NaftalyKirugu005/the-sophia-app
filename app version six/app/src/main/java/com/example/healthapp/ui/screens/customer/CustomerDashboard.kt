package com.example.healthapp.ui.screens.customer

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomerDashboard(
    onNavigateToMedicineCatalog: () -> Unit,
    onNavigateToSos: () -> Unit,
    onNavigateToReminders: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = onNavigateToSos,
                containerColor = Color.Red,
                contentColor = Color.White
            ) {
                Icon(Icons.Default.Warning, contentDescription = "SOS")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to Aliya", style = MaterialTheme.typography.headlineMedium)
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = onNavigateToMedicineCatalog,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Search Medicines")
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Button(
                onClick = onNavigateToReminders,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Medication Reminders")
            }
        }
    }
}
