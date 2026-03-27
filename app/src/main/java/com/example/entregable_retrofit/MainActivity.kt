package com.example.entregable_retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.content.Intent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        // Botón para ir a la actividad 1 (lista de posts)
        val btnAct1 = findViewById<Button>(R.id.btnAct1)
        btnAct1.setOnClickListener {
            val intent = Intent(this, Act1Activity::class.java)
            startActivity(intent)
        }
        
        // Botón para ir a la actividad 2 (búsqueda de post)
        val btnAct2 = findViewById<Button>(R.id.btnAct2)
        btnAct2.setOnClickListener {
            val intent = Intent(this, Act2Activity::class.java)
            startActivity(intent)
        }
    }
}