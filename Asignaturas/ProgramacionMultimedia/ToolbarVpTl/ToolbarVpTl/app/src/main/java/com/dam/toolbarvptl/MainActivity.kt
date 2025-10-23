package com.dam.toolbarvptl

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var tbCard: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setSupportActionBar(findViewById(R.id.toolbarPrincipal))
        supportActionBar?.setDisplayShowTitleEnabled(false);

        //bindear
        tbCard=findViewById(R.id.tbCard)
        //inflamos menu a toolbar del cardview
        tbCard.inflateMenu(R.menu.menu_tarjeta)
        //oyentes
        tbCard.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_borrar -> {
                    Log.i("ActionBar", "Acción borrar")
                    true
                }

                else -> {
                    true
                }
            }
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {

        R.id.action_file -> {
            Log.i("ActionBar", "Archivo!")
            true
        }
        R.id.action_buscar -> {
            Log.i("ActionBar", "Buscar!")
            true
        }
        R.id.action_opciones -> {
            Log.i("ActionBar", "Opciones!")
            true
        }
        R.id.action_salir -> {
            Log.i("ActionBar", "Salir!")
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }
}