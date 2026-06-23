package com.example.healthapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.healthapp.data.local.dao.HealthDao
import com.example.healthapp.data.local.entity.*

@Database(
    entities = [
        UserEntity::class,
        MedicineEntity::class,
        CartItemEntity::class,
        OrderEntity::class,
        OrderItemEntity::class,
        PrescriptionEntity::class,
        EmergencyContactEntity::class,
        MedicationReminderEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AliyaDatabase : RoomDatabase() {
    abstract fun healthDao(): HealthDao
}
