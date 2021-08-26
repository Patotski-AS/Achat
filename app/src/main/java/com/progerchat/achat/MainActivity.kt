package com.progerchat.achat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.progerchat.achat.databinding.ActivityMainBinding
import com.progerchat.achat.databinding.NavHeaderBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var headerBinding: NavHeaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        headerBinding = NavHeaderBinding.inflate(layoutInflater)
        headerBinding.apply {
            emaiTextView.text = "Test"
        }

        val navigationView = binding.navView
        navigationView.addHeaderView(headerBinding.root)

        val navController = this.findNavController(R.id.myNavHostFragment)
        NavigationUI.setupWithNavController(navigationView, navController)
    }

}