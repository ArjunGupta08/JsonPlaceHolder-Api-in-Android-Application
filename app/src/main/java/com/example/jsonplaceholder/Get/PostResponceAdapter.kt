package com.example.jsonplaceholder.Get

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.jsonplaceholder.Get.GetById.CommentByIdActivity
import com.example.jsonplaceholder.R

class PostResponceAdapter(val context:Context, val itemList : List<PostApiResponce>) : RecyclerView.Adapter<PostResponceAdapter.JsonViewHolder>() {

    class JsonViewHolder(itemView: View) : ViewHolder(itemView){
        val userId = itemView.findViewById<TextView>(R.id.userId)
        val postId = itemView.findViewById<TextView>(R.id.postId)
        val title = itemView.findViewById<TextView>(R.id.title)
        val textBody = itemView.findViewById<TextView>(R.id.body)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JsonViewHolder {
        val inflater : LayoutInflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.json_layout,parent,false)
        return JsonViewHolder(view)
    }
    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: JsonViewHolder, position: Int) {
        val item : PostApiResponce = itemList[position]
        holder.title.text = item.title
        holder.textBody.text = item.body
        val user = item.userId.toString()
        val post = item.id.toString()
        holder.userId.text = "User Id : $user"
        holder.postId.text = "Post Id : $post"

        holder.itemView.setOnClickListener {
            val intent = Intent(context, CommentByIdActivity::class.java)
            intent.putExtra("postId", item.id.toString())
            context.startActivity(intent)
        }
    }
}