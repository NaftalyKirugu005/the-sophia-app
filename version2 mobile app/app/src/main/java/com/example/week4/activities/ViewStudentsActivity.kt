package com.example.week4.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.R
import com.example.week4.adapter.StudentAdapter
import com.example.week4.db.DatabaseHelper
import com.example.week4.model.Student

class ViewStudentsActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private lateinit var adapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_students)

        db = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        loadData()
    }

    private fun loadData() {
        val students = db.getAllStudents()
        adapter = StudentAdapter(students, 
            onEditClick = { student ->
                val intent = Intent(this, UpdateStudentActivity::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
            },
            onDeleteClick = { student ->
                showDeleteDialog(student)
            }
        )
        recyclerView.adapter = adapter
    }

    private fun showDeleteDialog(student: Student) {
        AlertDialog.Builder(this)
            .setTitle("Delete Student")
            .setMessage("Are you sure you want to delete ${student.fullName}?")
            .setPositiveButton("Yes") { _, _ ->
                db.deleteStudent(student.id)
                loadData()
            }
            .setNegativeButton("No", null)
            .show()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }
}
