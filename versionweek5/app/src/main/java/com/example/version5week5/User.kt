package com.example.version5week5

import com.google.gson.annotations.SerializedName

/**
 * Data class representing a User from the JSONPlaceholder API.
 * Each field matches a key in the JSON response.
 */
data class User(
    @SerializedName("id")
    val id: Int,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("email")
    val email: String,
    
    @SerializedName("phone")
    val phone: String
)
