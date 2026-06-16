package com.example.week4.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.R
import com.example.week4.model.Student

class StudentAdapter(
    private var studentList: List<Student>,
    private val onEditClick: (Student) -> Unit,
    private val onDeleteClick: (Student) -> Unit
) : RecyclerView.Adapter<StudentAdapter.StudentViewHolder>() {

    class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvStudentName)
        val tvId: TextView = view.findViewById(R.id.tvStudentId)
        val tvCourse: TextView = view.findViewById(R.id.tvStudentCourse)
        val tvEmail: TextView = view.findViewById(R.id.tvStudentEmail)
        val btnEdit: Button = view.findViewById(R.id.btnEdit)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_student, parent, false)
        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val student = studentList[position]
        holder.tvName.text = student.fullName
        holder.tvId.text = "ID: ${student.id}"
        holder.tvCourse.text = "Course: ${student.course}"
        holder.tvEmail.text = "Email: ${student.email}"

        holder.btnEdit.setOnClickListener { onEditClick(student) }
        holder.btnDelete.setOnClickListener { onDeleteClick(student) }
    }

    override fun getItemCount(): Int = studentList.size

    fun updateData(newList: List<Student>) {
        this.studentList = newList
        notifyDataSetChanged()
    }
}
