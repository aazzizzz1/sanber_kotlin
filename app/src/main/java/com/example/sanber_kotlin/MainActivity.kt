package com.example.sanber_kotlin
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.sanber_kotlin.fragment.DashboardFragment
import com.example.sanber_kotlin.fragment.HomeFragment
import com.example.sanber_kotlin.fragment.NetworkingFragment
import com.example.sanber_kotlin.fragment.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment())

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    loadFragment(HomeFragment())
                }

                R.id.menu_dashboard -> {
                    loadFragment(DashboardFragment())
                }

                R.id.menu_profile -> {
                    loadFragment(ProfileFragment())
                }
                R.id.menu_task -> {
                    loadFragment(NetworkingFragment())
                }
            }
            true
        }
    }

    private fun loadFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}