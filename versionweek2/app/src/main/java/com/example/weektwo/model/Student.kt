package com.example.weektwo.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "students")
data class Student(
    @PrimaryKey
    val studentId: String,
    val fullName: String,
    val email: String,
    val phoneNumber: String,
    val course: String
)
