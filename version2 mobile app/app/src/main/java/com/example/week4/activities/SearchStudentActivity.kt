package com.example.week4.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.R
import com.example.week4.adapter.StudentAdapter
import com.example.week4.db.DatabaseHelper
import com.google.android.material.textfield.TextInputEditText

class SearchStudentActivity : AppCompatActivity() {

    private lateinit var db: DatabaseHelper
    private lateinit var adapter: StudentAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_student)

        db = DatabaseHelper(this)
        val etSearch = findViewById<TextInputEditText>(R.id.etSearch)
        val btnSearch = findViewById<Button>(R.id.btnSearch)
        recyclerView = findViewById(R.id.recyclerViewSearch)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(emptyList(),
            onEditClick = { student ->
                val intent = Intent(this, UpdateStudentActivity::class.java)
                intent.putExtra("student", student)
                startActivity(intent)
            },
            onDeleteClick = { student ->
                db.deleteStudent(student.id)
                performSearch(etSearch.text.toString())
            }
        )
        recyclerView.adapter = adapter

        btnSearch.setOnClickListener {
            performSearch(etSearch.text.toString())
        }
    }

    private fun performSearch(query: String) {
        val results = db.searchStudents(query)
        adapter.updateData(results)
    }
}
