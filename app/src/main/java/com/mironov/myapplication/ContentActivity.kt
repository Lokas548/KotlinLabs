package com.mironov.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class ContentActivity : AppCompatActivity() {

    private lateinit var  navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content)
        val navigation = findViewById<BottomNavigationView>(R.id.bottom_nav)
        navController = Navigation.findNavController(this,R.id.nav_host_fragment_container)

        navigation.setupWithNavController(navController)
    }
}