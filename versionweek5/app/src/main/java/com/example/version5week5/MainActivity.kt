package com.example.version5week5

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Main Activity that fetches user data from the API and displays it.
 */
class MainActivity : ComponentActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var errorTextView: TextView
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the content view to the XML layout
        setContentView(R.layout.activity_main)

        // Initialize UI components
        recyclerView = findViewById(R.id.recyclerViewUsers)
        progressBar = findViewById(R.id.progressBar)
        errorTextView = findViewById(R.id.textViewError)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch users from the API
        fetchUsers()
    }

    /**
     * Performs an asynchronous network call to retrieve users.
     */
    private fun fetchUsers() {
        // Show loading spinner
        progressBar.visibility = View.VISIBLE
        errorTextView.visibility = View.GONE

        // Get the ApiService from the singleton RetrofitClient
        val apiService = RetrofitClient.apiService
        
        // Make the GET request asynchronously
        apiService.getUsers().enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                // Hide loading spinner
                progressBar.visibility = View.GONE

                if (response.isSuccessful && response.body() != null) {
                    // Update UI with the retrieved users
                    val users = response.body()!!
                    userAdapter = UserAdapter(users)
                    recyclerView.adapter = userAdapter
                } else {
                    // Handle API error (e.g., 404, 500)
                    showError()
                }
            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
                // Hide loading spinner and handle network failures (e.g., timeout)
                progressBar.visibility = View.GONE
                showError()
            }
        })
    }

    /**
     * Displays a user-friendly error message.
     */
    private fun showError() {
        errorTextView.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }
}
