package com.mobile.pdhayi

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mobile.pdhayi.fragment.ChatFragment
import com.mobile.pdhayi.fragment.LectureFragment
import com.mobile.pdhayi.fragment.ProfileFragment
import com.mobile.pdhayi.fragment.SearchFragment
import com.mobile.pdhayi.fragment.TabsFragment

class HomeActivity : AppCompatActivity(), ChatFragment.OnBackButtonClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation_view)
        @Suppress("DEPRECATION")
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        // Set default fragment
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, TabsFragment())
                .commit()
        }
    }

    @Suppress("DEPRECATION")
    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        var selectedFragment: Fragment? = null
        when (item.itemId) {
            R.id.navigation_home -> selectedFragment = TabsFragment()
            R.id.navigation_search -> selectedFragment = SearchFragment()
            R.id.navigation_add_post -> {
                val intent = Intent(this, AddPost::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_lecture -> selectedFragment = LectureFragment()
            R.id.navigation_profile -> selectedFragment = ProfileFragment()
        }
        if (selectedFragment != null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()
        }
        true
    }

    override fun onBackButtonClicked() {
        // Switch to the Home tab when the back button is clicked in the ChatFragment
        val tabsFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as? TabsFragment
        tabsFragment?.onBackButtonClicked()
    }
}