package com.example.healthapp.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medicines")
data class MedicineEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val category: String,
    val price: Double,
    val stock: Int,
    val pharmacyId: String,
    val imageUrl: String = ""
)

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val medicineId: String,
    val medicineName: String,
    val price: Double,
    val quantity: Int,
    val userId: String
)