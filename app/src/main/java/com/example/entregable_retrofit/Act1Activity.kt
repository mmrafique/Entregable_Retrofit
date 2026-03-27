package com.example.entregable_retrofit

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.entregable_retrofit.adapters.PostAdapter
import com.example.entregable_retrofit.models.Post
import com.example.entregable_retrofit.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Act1Activity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    private lateinit var btnCargar: Button
    private lateinit var progressBar: ProgressBar
    private val postAdapter = PostAdapter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act1)
        
        recyclerView = findViewById(R.id.recyclerViewPosts)
        btnCargar = findViewById(R.id.btnCargar)
        progressBar = findViewById(R.id.progressBar)
        
        // Configurar el RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = postAdapter
        
        // Cargar los posts cuando se presiona el botón
        btnCargar.setOnClickListener {
            cargarPosts()
        }
    }
    
    private fun cargarPosts() {
        progressBar.visibility = ProgressBar.VISIBLE
        btnCargar.isEnabled = false
        
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val posts = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getAllPosts()
                }
                
                postAdapter.actualizarLista(posts)
                progressBar.visibility = ProgressBar.GONE
                btnCargar.isEnabled = true
                
                Toast.makeText(
                    this@Act1Activity,
                    "Se cargaron ${posts.size} posts",
                    Toast.LENGTH_SHORT
                ).show()
            } catch (e: Exception) {
                progressBar.visibility = ProgressBar.GONE
                btnCargar.isEnabled = true
                Toast.makeText(
                    this@Act1Activity,
                    "Error al cargar los posts: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
