package com.example.week4.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.week4.models.User
import kotlinx.coroutines.launch
import java.io.IOException

/**
 * ViewModel for managing UI-related data in a lifecycle-conscious way
 */
class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val users: MutableLiveData<List<User>> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    /**
     * Fetch users using Coroutines
     */
    fun fetchUsers() {
        isLoading.value = true
        viewModelScope.launch {
            try {
                val response = repository.getUsers()
                if (response.isSuccessful) {
                    users.value = response.body()
                } else {
                    handleApiError(response.code())
                }
            } catch (e: IOException) {
                errorMessage.value = "No Internet Connection or Network Timeout"
            } catch (e: Exception) {
                errorMessage.value = "An unexpected error occurred: ${e.message}"
            } finally {
                isLoading.value = false
            }
        }
    }

    private fun handleApiError(code: Int) {
        when (code) {
            404 -> errorMessage.value = "Error 404: Resource Not Found"
            500 -> errorMessage.value = "Error 500: Internal Server Error"
            else -> errorMessage.value = "API Error: $code"
        }
    }
}
