package com.example.healthapp.data.repository

import com.google.ai.client.generativeai.GenerativeModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AiRepository @Inject constructor(
    private val generativeModel: GenerativeModel
) {
    suspend fun checkSymptoms(symptoms: String): String {
        val prompt = """
            Analyze the following symptoms and provide a response in the following format:
            Risk Level: [Low/Moderate/High/Emergency]
            Possible Causes: [List potential causes]
            Recommendations: [What the user should do]
            
            IMPORTANT: End with "This is not a medical diagnosis. Consult a healthcare professional for medical advice."
            
            Symptoms: $symptoms
        """.trimIndent()

        return try {
            val response = generativeModel.generateContent(prompt)
            response.text ?: "Could not get a response from AI."
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }

    suspend fun getMedicineGuidance(medicineName: String): String {
        val prompt = "Provide dosage instructions, side effects, and precautions for $medicineName. End with 'Consult a doctor for medical advice.'"
        return try {
            val response = generativeModel.generateContent(prompt)
            response.text ?: "No guidance available."
        } catch (e: Exception) {
            "Error: ${e.message}"
        }
    }
}
