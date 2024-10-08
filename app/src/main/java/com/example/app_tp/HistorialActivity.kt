package com.example.app_tp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class HistorialActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: InversionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historial)



        recyclerView = findViewById(R.id.recycler_view) // Asegúrate de que este ID coincide con el que usas en tu layout
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listaInversiones = cargarLista()
        adapter = InversionAdapter(listaInversiones)
        recyclerView.adapter = adapter



    }

    public fun cargarLista(): MutableList<Inversiones> {
        val sharedPreferences = getSharedPreferences("MiLista", MODE_PRIVATE)
        val gson = Gson()
        val json = sharedPreferences.getString("LISTA", null)

        val type = object : TypeToken<MutableList<Inversiones>>() {}.type
        return if (json != null) {
            gson.fromJson(json, type)
        } else {
            mutableListOf() // Devuelve una lista vacía si no hay datos
        }
    }



}