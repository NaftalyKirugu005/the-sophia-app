package com.example.week4.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week4.R
import com.example.week4.db.DatabaseHelper
import com.example.week4.model.Student
import com.google.android.material.textfield.TextInputEditText

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_student)

        val etId = findViewById<TextInputEditText>(R.id.etStudentId)
        val etName = findViewById<TextInputEditText>(R.id.etFullName)
        val etCourse = findViewById<TextInputEditText>(R.id.etCourse)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
        val btnAdd = findViewById<Button>(R.id.btnAddStudent)

        val db = DatabaseHelper(this)

        btnAdd.setOnClickListener {
            val id = etId.text.toString()
            val name = etName.text.toString()
            val course = etCourse.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()

            if (id.isEmpty() || name.isEmpty() || course.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (db.checkStudentId(id)) {
                Toast.makeText(this, "Student ID already exists", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val student = Student(id, name, course, email, phone)
            val result = db.addStudent(student)

            if (result > -1) {
                Toast.makeText(this, "Student added successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error adding student", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
