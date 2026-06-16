package com.example.week4.activities

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.week4.R
import com.example.week4.db.DatabaseHelper
import com.example.week4.model.Student
import com.google.android.material.textfield.TextInputEditText

class UpdateStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_student)

        val etId = findViewById<TextInputEditText>(R.id.etStudentId)
        val etName = findViewById<TextInputEditText>(R.id.etFullName)
        val etCourse = findViewById<TextInputEditText>(R.id.etCourse)
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPhone = findViewById<TextInputEditText>(R.id.etPhone)
        val btnUpdate = findViewById<Button>(R.id.btnUpdateStudent)

        val db = DatabaseHelper(this)

        val student = intent.getSerializableExtra("student") as? Student

        student?.let {
            etId.setText(it.id)
            etName.setText(it.fullName)
            etCourse.setText(it.course)
            etEmail.setText(it.email)
            etPhone.setText(it.phoneNumber)
        }

        btnUpdate.setOnClickListener {
            val name = etName.text.toString()
            val course = etCourse.text.toString()
            val email = etEmail.text.toString()
            val phone = etPhone.text.toString()

            if (name.isEmpty() || course.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val updatedStudent = Student(student?.id ?: "", name, course, email, phone)
            val result = db.updateStudent(updatedStudent)

            if (result > 0) {
                Toast.makeText(this, "Student updated successfully", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                Toast.makeText(this, "Error updating student", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
