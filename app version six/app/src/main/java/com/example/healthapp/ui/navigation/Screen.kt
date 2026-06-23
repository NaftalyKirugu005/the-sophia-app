package com.example.healthapp.ui.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Login : Screen("login")
    object Register : Screen("register")
    object CustomerDashboard : Screen("customer_dashboard")
    object PharmacyDashboard : Screen("pharmacy_dashboard")
    object RiderDashboard : Screen("rider_dashboard")
    object MedicineCatalog : Screen("medicine_catalog")
    object MedicineDetails : Screen("medicine_details/{medicineId}") {
        fun createRoute(medicineId: String) = "medicine_details/$medicineId"
    }
    object Cart : Screen("cart")
    object EmergencySos : Screen("emergency_sos")
    object MedicationReminders : Screen("medication_reminders")
    object Profile : Screen("profile")
    object AiAssistant : Screen("ai_assistant")
}
