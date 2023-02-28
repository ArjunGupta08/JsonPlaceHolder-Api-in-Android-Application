package com.example.jsonplaceholder.Get.GetById

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.API.JsonInterfaceObject
import com.example.jsonplaceholder.Get.CommentApiResponce
import com.example.jsonplaceholder.Get.CommentResponceAdapter
import com.example.jsonplaceholder.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class CommentByIdActivity : AppCompatActivity() {

    lateinit var recyclerCommentId : RecyclerView
    lateinit var commenIdAdapter : CommentResponceAdapter
    lateinit var text_ : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment_by_id)

        recyclerCommentId = findViewById(R.id.recycler_comment_by_id)
        recyclerCommentId.setHasFixedSize(true)
        recyclerCommentId.layoutManager = LinearLayoutManager(this)
        getCommentById()
    }

    private fun getCommentById() {
        text_ = findViewById(R.id.comment)

        val commentId = intent.getStringExtra("postId")
        val id = commentId!!.toInt()

        val getComment = JsonInterfaceObject.retrofit.getCommentsById(id)
        getComment.enqueue(object : Callback<List<CommentApiResponce>?> {
            override fun onResponse(
                call: Call<List<CommentApiResponce>?>,
                response: Response<List<CommentApiResponce>?>
            ) {
                val response = response.body()!!
                commenIdAdapter = CommentResponceAdapter(this@CommentByIdActivity, response)
                commenIdAdapter.notifyDataSetChanged()
                recyclerCommentId.adapter = commenIdAdapter

                text_.text = "All Comments On the Post No. : $id"
            }

            override fun onFailure(call: Call<List<CommentApiResponce>?>, t: Throwable) {
                Toast.makeText(this@CommentByIdActivity,t.localizedMessage,Toast.LENGTH_LONG)
                    .show()
            }
        })
    }
}