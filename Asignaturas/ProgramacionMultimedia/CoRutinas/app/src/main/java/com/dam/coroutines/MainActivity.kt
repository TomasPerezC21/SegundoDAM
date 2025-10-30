package com.dam.coroutines

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        iniciarSumaParalela()
    }

    private fun iniciarSumaParalela() {
        lifecycleScope.launch {

            val numeros = 100000000
            val numPartes = 4
            val rangoPorParte = numeros / numPartes

            println("Iniciando Coroutines (4 Hilos Paralelos)")
            val startTime = System.currentTimeMillis()
            val totalSum = coroutineScope {
                val deferredSumas: List<Deferred<Long>> = (0 until numPartes).map { i ->
                    async(Dispatchers.Default) {
                        var sum = 0L
                        val start = i * rangoPorParte
                        val end = start + rangoPorParte
                        for (j in start until end) {
                            sum += j
                        }
                        // Devolvemos la suma parcial
                        return@async sum
                    }
                }


                // Esperar todos los resultados y sumarlos
                deferredSumas.awaitAll().sum()
            }


            val endTime = System.currentTimeMillis()


            // Cambiamos al Main Dispatcher para imprimir/actualizar la UI
            withContext(Dispatchers.Main) {
                println("Suma total: $totalSum")
                println("Tiempo total: ${endTime - startTime} ms")
            }
        }
    }

}