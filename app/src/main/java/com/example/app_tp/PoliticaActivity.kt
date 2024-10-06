package com.example.app_tp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class PoliticaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_politica)

        val btnBackPolitica = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnBackPolitica)

        btnBackPolitica.setOnClickListener{
            onBackPressedDispatcher
        }

    }
}