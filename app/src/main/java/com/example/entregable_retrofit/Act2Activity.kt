package com.example.entregable_retrofit

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.Toast
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.entregable_retrofit.adapters.CommentAdapter
import com.example.entregable_retrofit.models.Comment
import com.example.entregable_retrofit.models.Post
import com.example.entregable_retrofit.network.ApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Act2Activity : AppCompatActivity() {
    
    private lateinit var editTextPostId: EditText
    private lateinit var btnBuscar: Button
    private lateinit var recyclerViewComentarios: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var postContainer: LinearLayout
    private lateinit var tvPostTitle: TextView
    private lateinit var tvPostBody: TextView
    private val commentAdapter = CommentAdapter()
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act2)
        
        editTextPostId = findViewById(R.id.editTextPostId)
        btnBuscar = findViewById(R.id.btnBuscar)
        recyclerViewComentarios = findViewById(R.id.recyclerViewComentarios)
        progressBar = findViewById(R.id.progressBar)
        postContainer = findViewById(R.id.postContainer)
        tvPostTitle = findViewById(R.id.tvPostTitle)
        tvPostBody = findViewById(R.id.tvPostBody)
        
        // Configurar el RecyclerView
        recyclerViewComentarios.layoutManager = LinearLayoutManager(this)
        recyclerViewComentarios.adapter = commentAdapter
        
        // Buscar post y comentarios
        btnBuscar.setOnClickListener {
            buscarPostYComentarios()
        }
    }
    
    private fun buscarPostYComentarios() {
        val postId = editTextPostId.text.toString().trim()
        
        if (postId.isEmpty()) {
            Toast.makeText(
                this,
                "Por favor ingresa un número de post válido",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        
        val id = postId.toIntOrNull()
        if (id == null || id < 1 || id > 100) {
            Toast.makeText(
                this,
                "Ingresa un número entre 1 y 100",
                Toast.LENGTH_SHORT
            ).show()
            return
        }
        
        progressBar.visibility = ProgressBar.VISIBLE
        btnBuscar.isEnabled = false
        
        CoroutineScope(Dispatchers.Main).launch {
            try {
                val post = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getPostById(id)
                }
                
                val comentarios = withContext(Dispatchers.IO) {
                    ApiClient.apiService.getCommentsByPostId(id)
                }
                
                // Mostrar el post
                tvPostTitle.text = post.title
                tvPostBody.text = post.body
                postContainer.visibility = View.VISIBLE
                
                // Mostrar comentarios
                commentAdapter.actualizarLista(comentarios)
                progressBar.visibility = ProgressBar.GONE
                btnBuscar.isEnabled = true
                
            } catch (e: Exception) {
                progressBar.visibility = ProgressBar.GONE
                btnBuscar.isEnabled = true
                Toast.makeText(
                    this@Act2Activity,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
