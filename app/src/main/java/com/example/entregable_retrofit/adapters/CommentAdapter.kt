package com.example.entregable_retrofit.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.entregable_retrofit.R
import com.example.entregable_retrofit.models.Comment

class CommentAdapter : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>() {
    
    private var comentarios = emptyList<Comment>()
    
    inner class CommentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameView: TextView = itemView.findViewById(R.id.tvCommentName)
        private val emailView: TextView = itemView.findViewById(R.id.tvCommentEmail)
        private val bodyView: TextView = itemView.findViewById(R.id.tvCommentBody)
        
        fun bind(comment: Comment) {
            nameView.text = "Nombre: ${comment.name}"
            emailView.text = "Email: ${comment.email}"
            bodyView.text = "Comentario: ${comment.body}"
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }
    
    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(comentarios[position])
    }
    
    override fun getItemCount(): Int = comentarios.size
    
    fun actualizarLista(nuevaLista: List<Comment>) {
        comentarios = nuevaLista
        notifyDataSetChanged()
    }
}
