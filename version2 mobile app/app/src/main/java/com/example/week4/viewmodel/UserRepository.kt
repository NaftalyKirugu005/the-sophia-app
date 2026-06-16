package com.example.week4.viewmodel

import com.example.week4.network.RetrofitInstance
import com.example.week4.models.User
import retrofit2.Response

/**
 * Repository class to abstract data source from ViewModel
 */
class UserRepository {
    suspend fun getUsers(): Response<List<User>> {
        return RetrofitInstance.api.getUsers()
    }
}
