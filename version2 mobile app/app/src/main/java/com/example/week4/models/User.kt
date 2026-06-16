package com.example.week4.models

import com.google.gson.annotations.SerializedName

/**
 * Data model for User retrieved from JSONPlaceholder API
 */
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val phone: String,
    val website: String
)
