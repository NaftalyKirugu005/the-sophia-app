package com.example.week4.db

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.week4.model.Student

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "SophiaStudentDB"
        private const val DATABASE_VERSION = 1

        private const val TABLE_STUDENTS = "students"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "full_name"
        private const val COLUMN_COURSE = "course"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PHONE = "phone_number"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE " + TABLE_STUDENTS + "("
                + COLUMN_ID + " TEXT PRIMARY KEY,"
                + COLUMN_NAME + " TEXT,"
                + COLUMN_COURSE + " TEXT,"
                + COLUMN_EMAIL + " TEXT,"
                + COLUMN_PHONE + " TEXT" + ")")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_STUDENTS")
        onCreate(db)
    }

    /**
     * CRUD: Create - Add Student
     */
    fun addStudent(student: Student): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_ID, student.id)
        values.put(COLUMN_NAME, student.fullName)
        values.put(COLUMN_COURSE, student.course)
        values.put(COLUMN_EMAIL, student.email)
        values.put(COLUMN_PHONE, student.phoneNumber)

        val success = db.insert(TABLE_STUDENTS, null, values)
        db.close()
        return success
    }

    /**
     * CRUD: Read - View All Students
     */
    fun getAllStudents(): List<Student> {
        val studentList = mutableListOf<Student>()
        val db = this.readableDatabase
        val cursor: Cursor? = db.rawQuery("SELECT * FROM $TABLE_STUDENTS", null)

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val student = Student(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE))
                )
                studentList.add(student)
            } while (cursor.moveToNext())
        }
        cursor?.close()
        db.close()
        return studentList
    }

    /**
     * CRUD: Update - Update Student
     */
    fun updateStudent(student: Student): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NAME, student.fullName)
        values.put(COLUMN_COURSE, student.course)
        values.put(COLUMN_EMAIL, student.email)
        values.put(COLUMN_PHONE, student.phoneNumber)

        val success = db.update(TABLE_STUDENTS, values, "$COLUMN_ID=?", arrayOf(student.id))
        db.close()
        return success
    }

    /**
     * CRUD: Delete - Delete Student
     */
    fun deleteStudent(studentId: String): Int {
        val db = this.writableDatabase
        val success = db.delete(TABLE_STUDENTS, "$COLUMN_ID=?", arrayOf(studentId))
        db.close()
        return success
    }

    /**
     * Search Students by ID, Name, or Course
     */
    fun searchStudents(query: String): List<Student> {
        val studentList = mutableListOf<Student>()
        val db = this.readableDatabase
        val searchQuery = "SELECT * FROM $TABLE_STUDENTS WHERE $COLUMN_ID LIKE ? OR $COLUMN_NAME LIKE ? OR $COLUMN_COURSE LIKE ?"
        val cursor: Cursor? = db.rawQuery(searchQuery, arrayOf("%$query%", "%$query%", "%$query%"))

        if (cursor != null && cursor.moveToFirst()) {
            do {
                val student = Student(
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COURSE)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL)),
                    cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PHONE))
                )
                studentList.add(student)
            } while (cursor.moveToNext())
        }
        cursor?.close()
        db.close()
        return studentList
    }

    /**
     * Check if Student ID already exists
     */
    fun checkStudentId(id: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_STUDENTS WHERE $COLUMN_ID = ?", arrayOf(id))
        val exists = cursor.count > 0
        cursor.close()
        db.close()
        return exists
    }
}
