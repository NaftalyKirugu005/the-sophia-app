package com.example.week4

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week4.adapters.UserAdapter
import com.example.week4.viewmodel.UserRepository
import com.example.week4.viewmodel.UserViewModel
import com.example.week4.viewmodel.UserViewModelFactory

/**
 * Main Activity that displays the list of users from the API
 */
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_api)

        // Initialize RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewUsers)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(emptyList())
        recyclerView.adapter = adapter

        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        val tvError = findViewById<TextView>(R.id.tvError)

        // Setup ViewModel with Factory
        val repository = UserRepository()
        val factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[UserViewModel::class.java]

        // Observe users data
        viewModel.users.observe(this) { users ->
            if (users != null) {
                adapter.updateUsers(users)
                tvError.visibility = View.GONE
            }
        }

        // Observe loading state
        viewModel.isLoading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        // Observe error messages
        viewModel.errorMessage.observe(this) { message ->
            if (message != null) {
                tvError.text = message
                tvError.visibility = View.VISIBLE
            }
        }

        // Fetch data
        viewModel.fetchUsers()
    }
}
