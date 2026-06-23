package com.example.healthapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val repository: AuthRepository
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _userRole = MutableStateFlow<String?>(null)
    val userRole = _userRole.asStateFlow()

    fun login(email: String, pass: String, onSuccess: (String) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.login(email, pass).onSuccess { user ->
                _userRole.value = user.role
                onSuccess(user.role)
            }.onFailure {
                onError(it.message ?: "Login failed")
            }
            _isLoading.value = false
        }
    }

    fun register(email: String, pass: String, name: String, role: String, onSuccess: () -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            _isLoading.value = true
            repository.register(email, pass, name, role).onSuccess {
                onSuccess()
            }.onFailure {
                onError(it.message ?: "Registration failed")
            }
            _isLoading.value = false
        }
    }

    fun logout() {
        repository.logout()
        _userRole.value = null
    }
}
