package com.example.dataemployee

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dataemployee.databinding.ActivityMainBinding
import com.example.dataemployee.model.DataApi
import com.example.dataemployee.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var userAdapter: DataAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        fetchUsers()
        setUpBottomNavigation()
    }

    private fun setupRecyclerView() {
        userAdapter = DataAdapter(emptyList()) { user ->
            val intent = Intent(this, DetilData::class.java).apply {
                putExtra("firstName", user.first_name)
                putExtra("lastName", user.last_name)
                putExtra("email", user.email)
                putExtra("avatar", user.avatar)
            }
            startActivity(intent)
        }
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = userAdapter
        }
    }

        private fun fetchUsers() {
            showLoading(true)
            val client = ApiClient.getInstance()
            client.getAllUsers().enqueue(object : Callback<DataApi> {
                override fun onResponse(call: Call<DataApi>, response: Response<DataApi>) {
                    showLoading(false)
                    if (response.isSuccessful) {
                        response.body()?.data?.let { users ->
                            userAdapter.updateData(users)
                        } ?: showError("Response body is empty")
                    } else {
                        showError("Error: ${response.code()}")
                    }
                }
                override fun onFailure(call: Call<DataApi>, t: Throwable) {
                    showLoading(false)
                    showError("Connection error: ${t.message}")
                }
            })
        }


        private fun showLoading(isLoading: Boolean) {
            binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.rvUsers.visibility = if (isLoading) View.GONE else View.VISIBLE
        }

        private fun showError(message: String) {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
        }

        fun setUpBottomNavigation(){
            val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
            bottomNavigation.setOnItemSelectedListener { item ->
                when(item.itemId) {
                    R.id.home_navigation -> {
                        true
                    }
                    R.id.profile_navigation -> {
                        Toast.makeText(this@MainActivity, "COMING SOON", Toast.LENGTH_SHORT).show()
                        true
                    }
                    else -> false
                }
            }
        }

    }