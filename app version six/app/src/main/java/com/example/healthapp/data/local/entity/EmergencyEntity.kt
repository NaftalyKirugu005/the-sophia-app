package com.example.healthapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "emergency_contacts")
data class EmergencyContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val phoneNumber: String,
    val userId: String
)

@Entity(tableName = "medication_reminders")
data class MedicationReminderEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userId: String,
    val medicineName: String,
    val dosage: String,
    val time: String, // HH:mm
    val days: String, // Comma separated or bitmask
    val isActive: Boolean = true,
    val lastTaken: Long? = null
)
