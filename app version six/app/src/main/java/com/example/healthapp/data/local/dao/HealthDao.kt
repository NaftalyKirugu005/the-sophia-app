package com.example.healthapp.data.local.dao

import androidx.room.*
import com.example.healthapp.data.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao {
    // Users
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT * FROM users WHERE id = :userId")
    suspend fun getUserById(userId: String): UserEntity?

    // Medicines
    @Query("SELECT * FROM medicines")
    fun getAllMedicines(): Flow<List<MedicineEntity>>

    @Query("SELECT * FROM medicines WHERE name LIKE '%' || :query || '%'")
    fun searchMedicines(query: String): Flow<List<MedicineEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicine(medicine: MedicineEntity)

    // Cart
    @Query("SELECT * FROM cart_items WHERE userId = :userId")
    fun getCartItems(userId: String): Flow<List<CartItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(item: CartItemEntity)

    @Delete
    suspend fun removeFromCart(item: CartItemEntity)

    @Query("DELETE FROM cart_items WHERE userId = :userId")
    suspend fun clearCart(userId: String)

    // Orders
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE userId = :userId")
    fun getOrdersForUser(userId: String): Flow<List<OrderEntity>>

    // Medication Reminders
    @Query("SELECT * FROM medication_reminders WHERE userId = :userId")
    fun getReminders(userId: String): Flow<List<MedicationReminderEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReminder(reminder: MedicationReminderEntity)

    // Emergency Contacts
    @Query("SELECT * FROM emergency_contacts WHERE userId = :userId")
    fun getEmergencyContacts(userId: String): Flow<List<EmergencyContactEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmergencyContact(contact: EmergencyContactEntity)
}
