package com.example.version5week5

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object to provide a single instance of Retrofit throughout the app.
 */
object RetrofitClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    /**
     * Lazy initialization of the Retrofit instance.
     */
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * Provides the ApiService implementation.
     */
    val apiService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
