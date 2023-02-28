package com.example.jsonplaceholder.Get

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.API.JsonInterfaceObject
import com.example.jsonplaceholder.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CommentActivity : AppCompatActivity() {

    lateinit var commentAdapter : CommentResponceAdapter
    lateinit var recyclerComment : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        recyclerComment = findViewById(R.id.recyclerComment)
        recyclerComment.setHasFixedSize(true)
        recyclerComment.layoutManager = LinearLayoutManager(this)
        getComments()

    }

    private fun getComments() {
        val retroComment = JsonInterfaceObject.retrofit.getComments()
        retroComment.enqueue(object : Callback<List<CommentApiResponce>?> {
            override fun onResponse(
                call: Call<List<CommentApiResponce>?>,
                response: Response<List<CommentApiResponce>?>
            ) {
                if (response.isSuccessful) {
                    val responce = response.body()!!
                    Log.d("comments", responce.toString())
                    commentAdapter = CommentResponceAdapter(this@CommentActivity, responce)
                    commentAdapter.notifyDataSetChanged()
                    recyclerComment.adapter = commentAdapter
                }else{
                    Log.d("comments", response.errorBody().toString())
                }
            }
            override fun onFailure(call: Call<List<CommentApiResponce>?>, t: Throwable) {
                Toast.makeText(this@CommentActivity,t.localizedMessage,Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}