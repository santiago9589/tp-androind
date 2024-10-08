package com.example.app_tp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson


class HomeActivity : AppCompatActivity() {

    //INPUTS

    //INVERSION1
    var inputMonto1 =findViewById<TextView>(R.id.text_view_monto_1)
    var inputTasa1= findViewById<TextView>(R.id.text_view_tasa_1)
    var inputEntidad1 = findViewById<TextView>(R.id.text_view_entidad_1)
    var inputPlazo1 = findViewById<TextView>(R.id.text_view_plazo_1)
    var spinner1 = findViewById<Spinner>(R.id.spinner_1)
    // INVERSION1

    //INVERSION2
    var inputMonto2 = findViewById<TextView>(R.id.text_view_monto_2)
    var inputTasa2= findViewById<TextView>(R.id.text_view_tasa_2)
    var inputEntidad2 = findViewById<TextView>(R.id.text_view_entidad_2)
    var inputPlazo2 = findViewById<TextView>(R.id.text_view_plazo_2)
    var spinner2 = findViewById<Spinner>(R.id.spinner_2)
    // INVERSION2


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)





        // Lista de opciones
        var opciones = arrayOf("plazo fijo", "fondo comun")
        // Adaptador para el Spinner
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, opciones)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner1.adapter = adapter
        spinner2.adapter = adapter

        // BOTONES
        var btnResultado = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.calcular_btn)
        var btnHistorial = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.historial_btn)
        var btnTerminos = findViewById<androidx.appcompat.widget.AppCompatButton>(R.id.terminos_btn)


        btnHistorial.setOnClickListener{

            // lanzo el intent a la pagina de Historial

            val intent = Intent(this,HistorialActivity::class.java)
            startActivity(intent)
        }

        btnTerminos.setOnClickListener{

            // lanzo el intent a la pagina de Terminos

            val intent = Intent(this,PoliticaActivity::class.java)
            startActivity(intent)
        }

        btnResultado.setOnClickListener{

            // CALCULO INVERSION 1
            var inputMonto1Parsed: Float = inputMonto1.toString().toFloat()
            var inputTasa1Parsed: Float = inputTasa1.text.toString().toFloat()
            var inputPlazo1Parsed:Int = inputPlazo1.text.toString().toInt()

            var gananciaPlazo1:Float = inputTasa1Parsed*(inputTasa1Parsed/100)
            var plazoMensual1:Int = inputPlazo1Parsed/12
            var capitalFinal1:Float = inputMonto1Parsed + (gananciaPlazo1 * plazoMensual1)
            var ROIinversion1:Float = ((capitalFinal1-inputMonto1Parsed)/inputMonto1Parsed)

            // CALCULO INVERSION 2
            var inputMonto2Parsed: Float = inputMonto2.text.toString().toFloat()
            var inputTasa2Parsed: Float = inputTasa2.text.toString().toFloat()
            var inputPlazo2Parsed:Int = inputPlazo2.text.toString().toInt()

            var gananciaPlazo2:Float = inputTasa2Parsed*(inputTasa2Parsed/100)
            var plazoMensual2:Int = inputPlazo2Parsed/12
            var capitalFinal2:Float = inputMonto2Parsed + (gananciaPlazo2 * plazoMensual2)
            var ROIinversion2:Float = ((capitalFinal2-inputMonto2Parsed)/inputMonto2Parsed)

            // guardo las inversiones
            var inversionesClass = Inversiones(ROIinversion1.toString(),ROIinversion2.toString())
            agregarElemento(inversionesClass)

            // guardarInversiones(ROIinversion1.toString(),ROIinversion2.toString())

            // lanzo el intent a la pagina de resultado

            val intent = Intent(this,ResultadoActivity::class.java)
            intent.putExtra("INVERSION1",ROIinversion1)
            intent.putExtra("ENTIDAD1",inputEntidad1.toString())
            intent.putExtra("INVERSION2",ROIinversion2)
            intent.putExtra("ENTIDAD2",inputEntidad2.toString())
            startActivity(intent)
        }
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

    public fun agregarElemento(Inversiones: Inversiones) {
        // Cargar la lista existente
        val lista = cargarLista()

        // Agregar el nuevo elemento
        lista.add(Inversiones)

        // Guardar la lista actualizada
        guardarLista(lista)
    }

    public fun guardarLista(lista: List<Inversiones>) {
        val sharedPreferences = getSharedPreferences("MiLista", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        val gson = Gson()
        val json = gson.toJson(lista)

        editor.putString("LISTA", json)
        editor.apply()
    }


}