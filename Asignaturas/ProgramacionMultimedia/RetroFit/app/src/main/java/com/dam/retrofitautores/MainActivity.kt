package com.dam.retrofitautores

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() ,SearchView.OnQueryTextListener {


    private lateinit var recy: RecyclerView
    private lateinit var adaptadorAutores: AdaptadorAutores
    private lateinit var buscador: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //bindeo
        buscador=findViewById(R.id.svAutores)
        adaptadorAutores = AdaptadorAutores()
        recy=findViewById(R.id.rvAutores)

        recy.layoutManager = LinearLayoutManager(
            applicationContext,
            LinearLayoutManager.VERTICAL,false
        )
        recy.adapter=adaptadorAutores
        buscador.setOnQueryTextListener(this)


    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://openlibrary.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun busquedaAutores(query:String){
        lifecycleScope.launch(Dispatchers.IO) {

            val call=getRetrofit().create(APIservice::class.java).getAutores("search/authors.json?q=$query")
            val autoresApi=call.body()

            if (call.isSuccessful){
                //cargar info en recview
                withContext(Dispatchers.Main) {
                    val autores = autoresApi?.autores ?: emptyList()
                    adaptadorAutores.changeList(autores)
                }
            }else{
                //muestro toast con error
            }

        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if(!query.isNullOrEmpty()){
            busquedaAutores(query.lowercase())
        }


        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

}