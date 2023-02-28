package com.example.jsonplaceholder.Get.GetById

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.API.JsonInterfaceObject
import com.example.jsonplaceholder.Get.PostApiResponce
import com.example.jsonplaceholder.Get.PostResponceAdapter
import com.example.jsonplaceholder.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostByUserIdActivity : AppCompatActivity() {


    lateinit var postResponceIdAdapter: PostResponceAdapter
    lateinit var userHeadingView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_by_id)

        userHeadingView = findViewById(R.id.userPostByIdHeading)

        getPostById()
    }

    private fun getPostById() {
        val recyclerPostId = findViewById<RecyclerView>(R.id.recycler_post_by_id)
        recyclerPostId.setHasFixedSize(true)
        recyclerPostId.layoutManager = LinearLayoutManager(this)

        val userId = intent.getStringExtra("userId")
        val id = userId!!.toInt()
        userHeadingView.text = "All Posts By User No. : $id"

        val radio = intent.getStringExtra("radio")

     /*  Use While Using QueryMAp in API Interface
        val options : HashMap<String, String> = HashMap()
        options["_sort"] = "id"
        options["_order"] = "desc"
     */
        var order = radio.toString()

        if (radio == "Ascending"){
            order = "asc"
        }else{
            order = "desc"
        }

        val orderDescending = "desc"
        val orderAscending = "asc"
        val postById = JsonInterfaceObject.retrofit.getPostByUserId(id,"id",order)
        postById.enqueue(object : Callback<List<PostApiResponce>?> {
            override fun onResponse(call: Call<List<PostApiResponce>?>,
                                    response: Response<List<PostApiResponce>?>)
            {
                postResponceIdAdapter = PostResponceAdapter(this@PostByUserIdActivity,response.body()!!)
                postResponceIdAdapter.notifyDataSetChanged()
                recyclerPostId.adapter = postResponceIdAdapter

            }
            override fun onFailure(call: Call<List<PostApiResponce>?>, t: Throwable) {
                Toast.makeText(this@PostByUserIdActivity,t.localizedMessage, Toast.LENGTH_LONG)
                    .show()
            }
        })
    }

}