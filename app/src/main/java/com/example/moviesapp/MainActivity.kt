package com.example.moviesapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.moviesapp.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.FirebaseApp


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private lateinit var navController: NavController
    var bottomNavigationView: BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this);


        setUpNavigation()

    //    BottomNavigationView.OnNavigationItemSelectedListener { item ->
    //        when (item.itemId) {
    //            R.id.home -> {
    //                // Respond to navigation item 1 click
    //                true
    //            }
    //            R.id.explore -> {
    //                // Respond to navigation item 2 click
    //                true
    //            }
    //            R.id.confirmation -> {
    //                // Respond to navigation item 2 click
    //                true
    //            }
    //            R.id.person -> {
    //                // Respond to navigation item 2 click
    //                true
    //            }
    //            else -> false
    //        }
    //    }
    }

    fun setUpNavigation() {
        bottomNavigationView = binding.bottomNavigation
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        NavigationUI.setupWithNavController(
            bottomNavigationView!!,
            navHostFragment!!.navController,
        )
        //bottomNavigationView = findViewById(com.example.lookupweather.R.id.bottomNav)

    }



    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, null)
    }


}