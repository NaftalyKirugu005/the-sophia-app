package com.example.weektwo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weektwo.model.Student
import com.example.weektwo.ui.StudentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistrationScreen(
    viewModel: StudentViewModel,
    onBack: () -> Unit,
    studentToEdit: Student? = null
) {
    var studentId by remember { mutableStateOf(studentToEdit?.studentId ?: "") }
    var fullName by remember { mutableStateOf(studentToEdit?.fullName ?: "") }
    var email by remember { mutableStateOf(studentToEdit?.email ?: "") }
    var phoneNumber by remember { mutableStateOf(studentToEdit?.phoneNumber ?: "") }
    var course by remember { mutableStateOf(studentToEdit?.course ?: "") }
    
    var showError by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(if (studentToEdit == null) "Register Student" else "Update Student") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            OutlinedTextField(
                value = studentId,
                onValueChange = { studentId = it },
                label = { Text("Student ID") },
                modifier = Modifier.fillMaxWidth(),
                enabled = studentToEdit == null // ID is usually PK and shouldn't change
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = fullName,
                onValueChange = { fullName = it },
                label = { Text("Full Name") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email Address") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = { Text("Phone Number") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = course,
                onValueChange = { course = it },
                label = { Text("Course/Program") },
                modifier = Modifier.fillMaxWidth()
            )
            
            if (showError) {
                Text(
                    text = "Please fill all fields",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = {
                    if (studentId.isBlank() || fullName.isBlank() || email.isBlank() || 
                        phoneNumber.isBlank() || course.isBlank()) {
                        showError = true
                    } else {
                        val student = Student(studentId, fullName, email, phoneNumber, course)
                        if (studentToEdit == null) {
                            viewModel.insert(student)
                        } else {
                            viewModel.update(student)
                        }
                        onBack()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (studentToEdit == null) "Save Student" else "Update Record")
            }
        }
    }
}
