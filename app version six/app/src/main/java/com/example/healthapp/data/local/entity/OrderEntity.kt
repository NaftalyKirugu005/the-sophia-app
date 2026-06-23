package com.example.healthapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "orders")
data class OrderEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val totalAmount: Double,
    val status: String, // "PENDING", "APPROVED", "OUT_FOR_DELIVERY", "COMPLETED", "REJECTED"
    val timestamp: Long,
    val pharmacyId: String,
    val riderId: String? = null
)

@Entity(tableName = "order_items")
data class OrderItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val orderId: String,
    val medicineId: String,
    val quantity: Int,
    val price: Double
)

@Entity(tableName = "prescriptions")
data class PrescriptionEntity(
    @PrimaryKey val id: String,
    val userId: String,
    val imageUrl: String,
    val status: String, // "PENDING", "APPROVED", "REJECTED"
    val pharmacyId: String? = null,
    val timestamp: Long
)
