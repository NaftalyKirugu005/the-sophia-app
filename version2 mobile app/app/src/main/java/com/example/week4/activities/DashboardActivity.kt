package com.example.week4.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.week4.R
import com.example.week4.utils.SharedPrefManager
import com.google.android.material.card.MaterialCardView

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val pref = SharedPrefManager(this)
        if (pref.isDarkTheme()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        val cardAdd = findViewById<MaterialCardView>(R.id.cardAddStudent)
        val cardView = findViewById<MaterialCardView>(R.id.cardViewStudents)
        val cardSearch = findViewById<MaterialCardView>(R.id.cardSearchStudent)
        val cardTheme = findViewById<MaterialCardView>(R.id.cardTheme)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        tvWelcome.text = "Welcome, ${pref.getUsername()}!"

        cardAdd.setOnClickListener {
            startActivity(Intent(this, AddStudentActivity::class.java))
        }

        cardView.setOnClickListener {
            startActivity(Intent(this, ViewStudentsActivity::class.java))
        }

        cardSearch.setOnClickListener {
            startActivity(Intent(this, SearchStudentActivity::class.java))
        }

        cardTheme.setOnClickListener {
            val isDark = !pref.isDarkTheme()
            pref.setThemePreference(isDark)
            if (isDark) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            recreate()
        }

        btnLogout.setOnClickListener {
            pref.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}
