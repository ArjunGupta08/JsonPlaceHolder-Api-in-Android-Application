package com.example.jsonplaceholder.Get

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jsonplaceholder.API.JsonInterfaceObject
import com.example.jsonplaceholder.Get.GetById.PostByUserIdActivity
import com.example.jsonplaceholder.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostActivity : AppCompatActivity() {
    lateinit var userIdEdit : EditText
    lateinit var getBtn : Button
    lateinit var body : TextView
    lateinit var postResponceAdapter : PostResponceAdapter

    var radioGroup: RadioGroup? = null
    lateinit var radioButton: RadioButton

    lateinit var recyclerPost : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        findViewById<Button>(R.id.commentBtn).setOnClickListener {
            startActivity(Intent(this@PostActivity,CommentActivity::class.java))
        }

        userIdEdit = findViewById(R.id.postId)
        getBtn = findViewById(R.id.btnId)

        radioGroup = findViewById(R.id.radioGroup)


        getBtn.setOnClickListener {

            val intSelectButton: Int = radioGroup!!.checkedRadioButtonId
//            Toast.makeText(baseContext, radioButton.text, Toast.LENGTH_SHORT).show()

            if(radioGroup!!.checkedRadioButtonId == -1){
                Toast.makeText(this@PostActivity,"Select Order", Toast.LENGTH_SHORT)
                    .show()
        }else{
                radioButton = findViewById(intSelectButton)
                val userId = userIdEdit.text.toString()
                val intent = Intent(this@PostActivity,PostByUserIdActivity::class.java)
                intent.putExtra("userId", userId)
                intent.putExtra("radio", radioButton.text)
                startActivity(intent)
            }
    }
        recyclerPost = findViewById(R.id.recyclerPost)
        recyclerPost.setHasFixedSize(true)
        recyclerPost.layoutManager = LinearLayoutManager(this)

        getPostData()
//        getPatchData()
    }

    private fun getPatchData() {
        val patch = JsonInterfaceObject.retrofit.patchData(4, PostApiResponce("Name", 4,5,"Class"))
        patch.enqueue(object : Callback<PostApiResponce?> {
            override fun onResponse(
                call: Call<PostApiResponce?>,
                response: Response<PostApiResponce?>
            ) {
                if (response.isSuccessful){
                    Toast.makeText(this@PostActivity,response.body().toString(),Toast.LENGTH_SHORT)
                        .show()
                    Log.d("patch",response.body().toString())
                }
                 Toast.makeText(this@PostActivity,response.errorBody().toString(),Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onFailure(call: Call<PostApiResponce?>, t: Throwable) {
                Toast.makeText(this@PostActivity,t.localizedMessage,Toast.LENGTH_SHORT)
                    .show()
            }
        })
    }

    private fun getPostData() {
        val data = JsonInterfaceObject.retrofit.getPost()
        data.enqueue(object : Callback<List<PostApiResponce>?> {
            override fun onResponse(
                call: Call<List<PostApiResponce>?>,
                response: Response<List<PostApiResponce>?>
            ) {
                val responce = response.body()!!
                postResponceAdapter = PostResponceAdapter(this@PostActivity,responce)
                postResponceAdapter.notifyDataSetChanged()
                recyclerPost.adapter = postResponceAdapter
            }
            override fun onFailure(call: Call<List<PostApiResponce>?>, t: Throwable) {
               Toast.makeText(this@PostActivity,t.localizedMessage,Toast.LENGTH_LONG)
                   .show()
            }
        })
    }
}