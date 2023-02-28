package com.example.jsonplaceholder.Get

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jsonplaceholder.R

class CommentResponceAdapter(val context: Context, val listCom : List<CommentApiResponce>) : RecyclerView.Adapter<CommentResponceAdapter.CommentViewHolder>() {

    class CommentViewHolder(itemView: View) : ViewHolder(itemView){
        val id = itemView.findViewById<TextView>(R.id.userId)
        val commentId = itemView.findViewById<TextView>(R.id.postId)
        val email = itemView.findViewById<TextView>(R.id.title)
        val body = itemView.findViewById<TextView>(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.json_layout,parent,false)
        return CommentViewHolder(view)
    }

    override fun getItemCount(): Int {
        return listCom.size
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val item : CommentApiResponce = listCom[position]
        val postId = item.postId.toString()
        holder.id.text = "Post Id : $postId"
        val comment = item.id.toString()
        holder.commentId.text = "Comment Id : $comment"
        holder.email.text = item.email
        holder.body.text = item.body
    }
}