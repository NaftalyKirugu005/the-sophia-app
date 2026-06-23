package com.example.healthapp.data.repository

import com.example.healthapp.data.local.dao.HealthDao
import com.example.healthapp.data.local.entity.CartItemEntity
import com.example.healthapp.data.local.entity.MedicineEntity
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MedicineRepository @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val healthDao: HealthDao
) {
    val allMedicines: Flow<List<MedicineEntity>> = healthDao.getAllMedicines()

    suspend fun syncMedicines() {
        try {
            val snapshot = firestore.collection("medicines").get().await()
            snapshot.documents.forEach { doc ->
                val medicine = MedicineEntity(
                    id = doc.id,
                    name = doc.getString("name") ?: "",
                    description = doc.getString("description") ?: "",
                    category = doc.getString("category") ?: "",
                    price = doc.getDouble("price") ?: 0.0,
                    stock = doc.getLong("stock")?.toInt() ?: 0,
                    pharmacyId = doc.getString("pharmacyId") ?: "",
                    imageUrl = doc.getString("imageUrl") ?: ""
                )
                healthDao.insertMedicine(medicine)
            }
        } catch (e: Exception) {
            // Handle error
        }
    }

    fun searchMedicines(query: String) = healthDao.searchMedicines(query)

    suspend fun addToCart(item: CartItemEntity) = healthDao.addToCart(item)
    
    fun getCartItems(userId: String) = healthDao.getCartItems(userId)
    
    suspend fun removeFromCart(item: CartItemEntity) = healthDao.removeFromCart(item)
}
