package com.dam.examenrecuperacionandroidbasico2526

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var viewPager2: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private lateinit var miTab: TabLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Bindeo de la toolbar

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.miToolbar)
        setSupportActionBar(toolbar)

        miTab = findViewById(R.id.miTab)

        viewPager2=findViewById(R.id.viewPager)
        viewPagerAdapter=ViewPagerAdapter(this,2)
        viewPager2.adapter = viewPagerAdapter

        TabLayoutMediator(miTab, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "Form"
                1 -> tab.text = "Lista"
            }
        }.attach()



    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            val intent = Intent(this, Calificador::class.java,)
            startActivity(intent)
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}

