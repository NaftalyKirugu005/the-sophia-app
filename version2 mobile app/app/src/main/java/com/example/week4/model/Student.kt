package com.example.week4.model

import java.io.Serializable

/**
 * Data model for Student
 */
data class Student(
    val id: String,
    val fullName: String,
    val course: String,
    val email: String,
    val phoneNumber: String
) : Serializable
