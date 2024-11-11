package com.example.dataemployee

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView

class DetilData : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detil_data)
        val firstName = intent.getStringExtra("firstName")
        val lastName = intent.getStringExtra("lastName")
        val email = intent.getStringExtra("email")
        val avatar = intent.getStringExtra("avatar")
        findViewById<TextView>(R.id.name).text = "$firstName $lastName"
        findViewById<TextView>(R.id.email).text = email
        val imgProfile = findViewById<ImageView>(R.id.img_profile)
        Glide.with(this)
            .load(avatar)
            .into(imgProfile)

        setUpBottomNavigation()
    }
    fun setUpBottomNavigation(){
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.profile_navigation  -> {
                    intent = Intent(this@DetilData, MainActivity::class.java)
                    startActivity(intent);
                    true
                }
                R.id.profile_navigation -> {
                    true
                }
                else -> false
            }
        }
    }
}