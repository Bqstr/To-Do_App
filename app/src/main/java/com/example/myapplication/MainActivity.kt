package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
   // lateinit var navHostFragment: NavHostFragment
   // lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        super.onCreate(savedInstanceState)// ikd what is the problem try to remove biinding hz
//        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.navController) as NavHostFragment
        navController = navHostFragment.findNavController()
        setupActionBarWithNavController(navController)

    }
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


}