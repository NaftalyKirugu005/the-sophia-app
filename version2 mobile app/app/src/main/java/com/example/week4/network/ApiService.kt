package com.example.week4.network

import com.example.week4.models.User
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit interface defining the API endpoints
 */
interface ApiService {
    
    // GET request to retrieve the list of users
    @GET("users")
    suspend fun getUsers(): Response<List<User>>
}
