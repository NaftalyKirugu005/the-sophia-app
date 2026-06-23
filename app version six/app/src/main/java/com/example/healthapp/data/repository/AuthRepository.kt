package com.example.healthapp.data.repository

import com.example.healthapp.data.local.dao.HealthDao
import com.example.healthapp.data.local.entity.UserEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firestore: FirebaseFirestore,
    private val healthDao: HealthDao
) {
    val currentUser get() = firebaseAuth.currentUser

    suspend fun login(email: String, pass: String): Result<UserEntity> {
        return try {
            val authResult = firebaseAuth.signInWithEmailAndPassword(email, pass).await()
            val userId = authResult.user?.uid ?: throw Exception("Login failed")
            
            val userDoc = firestore.collection("users").document(userId).get().await()
            val role = userDoc.getString("role") ?: "CUSTOMER"
            val name = userDoc.getString("name") ?: ""
            
            val user = UserEntity(userId, name, email, role)
            healthDao.insertUser(user)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun register(email: String, pass: String, name: String, role: String): Result<UserEntity> {
        return try {
            val authResult = firebaseAuth.createUserWithEmailAndPassword(email, pass).await()
            val userId = authResult.user?.uid ?: throw Exception("Registration failed")
            
            val user = UserEntity(userId, name, email, role)
            
            // Save to Firestore
            firestore.collection("users").document(userId).set(
                mapOf(
                    "id" to userId,
                    "name" to name,
                    "email" to email,
                    "role" to role
                )
            ).await()
            
            // Save to Local DB
            healthDao.insertUser(user)
            
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun logout() {
        firebaseAuth.signOut()
    }
}
