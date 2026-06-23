package com.example.healthapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val id: String,
    val name: String,
    val email: String,
    val role: String, // "CUSTOMER", "PHARMACY", "RIDER", "ADMIN"
    val phoneNumber: String = "",
    val bloodGroup: String = "",
    val allergies: String = "",
    val conditions: String = ""
)