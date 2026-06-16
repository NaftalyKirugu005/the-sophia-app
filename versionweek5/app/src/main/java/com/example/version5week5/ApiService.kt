package com.example.version5week5

import retrofit2.Call
import retrofit2.http.GET

/**
 * Retrofit interface defining the API endpoints.
 */
interface ApiService {
    /**
     * Retrieves a list of users from the /users endpoint.
     * returns a Call object which can be executed asynchronously.
     */
    @GET("users")
    fun getUsers(): Call<List<User>>
}
