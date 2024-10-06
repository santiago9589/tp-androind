package com.example.app_tp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultadoActivity : AppCompatActivity() {


    // tomo los textos por id
    var textInversion1 = findViewById<TextView>(R.id.resulta_inversion_1)
    var textInversion2 = findViewById<TextView>(R.id.resulta_inversion_2)
    var textTotal = findViewById<TextView>(R.id.resulta_total)
    var btnBackResultado = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.btnBackResultado)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_resultado)

        // recibo el intent de la pagina principal
        val intent = intent
        var inversion1 = intent.getStringExtra("INVERSION1")
        var inversion2 = intent.getStringExtra("INVERSION2")

        textInversion1.text = "$inversion1"
        textInversion2.text = "$inversion2"

        btnBackResultado.setOnClickListener{
            onBackPressedDispatcher
        }

        if (inversion1 != null && inversion2 !=null) {
            if(inversion1.toFloat() > inversion2.toFloat()){
                textTotal.text = "La inversion 1 la mejor opcion"
            }else{
                textTotal.text = "La inversion 2 la mejor opcion"
            }
        }


    }
}